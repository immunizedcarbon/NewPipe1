package org.schabi.newpipe.core.data.repository.impl

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first
import org.schabi.newpipe.core.data.db.FeedDao
import org.schabi.newpipe.core.data.db.FeedItemEntity
import org.schabi.newpipe.core.data.repository.FeedRepository
import org.schabi.newpipe.core.data.repository.SubscriptionRepository
import org.schabi.newpipe.core.model.Stream
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.channel.ChannelInfo
import org.schabi.newpipe.extractor.stream.StreamInfoItem

class DefaultFeedRepository @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository,
    private val feedDao: FeedDao
) : FeedRepository {
    override fun getFeed(): Flow<List<Stream>> =
        feedDao.getFeed().map { list ->
            list.map { entity ->
                Stream(
                    url = entity.streamUrl,
                    channelUrl = null,
                    title = entity.title,
                    thumbnailUrl = entity.thumbnailUrl,
                    duration = 0L,
                    uploader = entity.channelName
                )
            }
        }

    override suspend fun refreshFeed() {
        val subscriptions = subscriptionRepository.getSubscriptions().first()
        val allItems = mutableListOf<StreamInfoItem>()
        for (channel in subscriptions) {
            val service = NewPipe.getServiceByUrl(channel.url)
            val info = ChannelInfo.getInfo(service, channel.url)
            allItems += info.relatedItems
        }
        val distinctItems = allItems.distinctBy { it.url }
            .sortedByDescending { it.uploadDate ?: 0L }
        val entities = distinctItems.map { item ->
            FeedItemEntity(
                streamUrl = item.url,
                channelName = item.uploaderName,
                channelAvatarUrl = item.uploaderAvatarUrl,
                uploadDate = item.uploadDate,
                title = item.name,
                thumbnailUrl = item.thumbnailUrl
            )
        }
        feedDao.replaceAll(entities)
    }
}
