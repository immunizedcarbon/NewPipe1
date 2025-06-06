package org.schabi.newpipe.core.data.repository

import kotlinx.coroutines.flow.Flow
import org.schabi.newpipe.core.model.Download

interface DownloadRepository {
    fun getDownloads(): Flow<List<Download>>
    suspend fun upsert(download: Download)
    suspend fun delete(streamUrl: String)
}
