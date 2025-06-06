package org.schabi.newpipe.core.data.repository

import kotlinx.coroutines.flow.Flow
import org.schabi.newpipe.core.model.Stream

interface FeedRepository {
    fun getFeed(): Flow<List<Stream>>
    suspend fun refreshFeed()
}
