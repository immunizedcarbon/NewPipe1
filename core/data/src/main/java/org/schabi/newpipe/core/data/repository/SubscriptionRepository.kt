package org.schabi.newpipe.core.data.repository

import kotlinx.coroutines.flow.Flow
import org.schabi.newpipe.core.model.Channel

interface SubscriptionRepository {
    fun getSubscriptions(): Flow<List<Channel>>
    suspend fun subscribe(channel: Channel)
    suspend fun unsubscribe(channel: Channel)
}
