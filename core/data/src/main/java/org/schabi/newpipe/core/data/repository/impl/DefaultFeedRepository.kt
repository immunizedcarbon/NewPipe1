package org.schabi.newpipe.core.data.repository.impl

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.schabi.newpipe.core.data.db.FeedDao
import org.schabi.newpipe.core.data.repository.FeedRepository
import org.schabi.newpipe.core.data.repository.SubscriptionRepository
import org.schabi.newpipe.core.model.Stream

class DefaultFeedRepository @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository,
    private val feedDao: FeedDao
) : FeedRepository {
    override fun getFeed(): Flow<List<Stream>> {
        return flowOf(emptyList())
    }

    override suspend fun refreshFeed() {
        // no-op
    }
}
