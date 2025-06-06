package org.schabi.newpipe.core.data.repository.impl

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.schabi.newpipe.core.data.db.DownloadDao
import org.schabi.newpipe.core.data.db.DownloadEntity
import org.schabi.newpipe.core.data.repository.DownloadRepository
import org.schabi.newpipe.core.model.Download

class DefaultDownloadRepository @Inject constructor(
    private val dao: DownloadDao
) : DownloadRepository {
    override fun getDownloads(): Flow<List<Download>> =
        dao.getDownloads().map { list ->
            list.map { it.toModel() }
        }

    override suspend fun upsert(download: Download) {
        dao.upsert(download.toEntity())
    }

    override suspend fun delete(streamUrl: String) {
        dao.delete(streamUrl)
    }
}

private fun DownloadEntity.toModel() = Download(
    streamUrl = streamUrl,
    title = title,
    thumbnailUrl = thumbnailUrl,
    localFilePath = localFilePath,
    mediaType = mediaType,
    status = status,
    progress = progress
)

private fun Download.toEntity() = DownloadEntity(
    streamUrl = streamUrl,
    title = title,
    thumbnailUrl = thumbnailUrl,
    localFilePath = localFilePath,
    mediaType = mediaType,
    status = status,
    progress = progress
)
