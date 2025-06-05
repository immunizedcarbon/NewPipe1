package org.schabi.newpipe.core.data.repository

import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    val isWatchHistoryEnabled: Flow<Boolean>
    suspend fun setWatchHistoryEnabled(enabled: Boolean)
}
