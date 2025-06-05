package org.schabi.newpipe.core.data.repository

import kotlinx.coroutines.flow.Flow
import org.schabi.newpipe.core.model.Stream

interface HistoryRepository {
    val isWatchHistoryEnabled: Flow<Boolean>
    fun getWatchHistory(): Flow<List<Stream>>
    suspend fun addStreamToHistory(stream: Stream)
    suspend fun setWatchHistoryEnabled(enabled: Boolean)
}
