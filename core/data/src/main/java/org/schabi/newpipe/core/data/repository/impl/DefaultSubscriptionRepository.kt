package org.schabi.newpipe.core.data.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.schabi.newpipe.core.data.db.SubscriptionDao
import org.schabi.newpipe.core.data.db.SubscriptionEntity
import org.schabi.newpipe.core.data.repository.SubscriptionRepository
import org.schabi.newpipe.core.model.Channel
import javax.inject.Inject

class DefaultSubscriptionRepository @Inject constructor(
    private val dao: SubscriptionDao
) : SubscriptionRepository {
    override fun getSubscriptions(): Flow<List<Channel>> =
        dao.getSubscriptions().map { list ->
            list.map { entity ->
                Channel(
                    url = entity.channelUrl,
                    name = entity.channelName,
                    avatarUrl = entity.channelAvatarUrl
                )
            }
        }

    override suspend fun subscribe(channel: Channel) {
        dao.insert(
            SubscriptionEntity(
                channelUrl = channel.url,
                channelName = channel.name,
                channelAvatarUrl = channel.avatarUrl,
                subscriptionDate = System.currentTimeMillis()
            )
        )
    }

    override suspend fun unsubscribe(channel: Channel) {
        dao.delete(
            SubscriptionEntity(
                channelUrl = channel.url,
                channelName = channel.name,
                channelAvatarUrl = channel.avatarUrl,
                subscriptionDate = 0L
            )
        )
    }
}
