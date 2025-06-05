package org.schabi.newpipe.core.data.repository.impl

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.schabi.newpipe.core.data.db.PlaylistDao
import org.schabi.newpipe.core.data.db.PlaylistEntity
import org.schabi.newpipe.core.data.db.PlaylistItemEntity
import org.schabi.newpipe.core.data.repository.PlaylistRepository
import org.schabi.newpipe.core.model.LocalPlaylist
import org.schabi.newpipe.core.model.Stream

class DefaultPlaylistRepository @Inject constructor(
    private val playlistDao: PlaylistDao
) : PlaylistRepository {
    override fun getPlaylists(): Flow<List<LocalPlaylist>> =
        playlistDao.getPlaylists().map { list ->
            list.map { entity ->
                LocalPlaylist(id = entity.id, name = entity.name, thumbnailUrl = entity.thumbnailUrl)
            }
        }

    override suspend fun createPlaylist(name: String, thumbnailUrl: String?) {
        playlistDao.insertPlaylist(PlaylistEntity(name = name, thumbnailUrl = thumbnailUrl))
    }

    override suspend fun deletePlaylist(id: Long) {
        playlistDao.deletePlaylist(id)
    }

    override fun getPlaylistItems(playlistId: Long): Flow<List<Stream>> =
        playlistDao.getPlaylistItems(playlistId).map { list ->
            list.map { item ->
                Stream(
                    url = item.streamUrl,
                    title = item.title,
                    thumbnailUrl = item.thumbnailUrl,
                    duration = 0L,
                    uploader = item.uploader
                )
            }
        }

    override suspend fun addStreamToPlaylist(playlistId: Long, stream: Stream) {
        playlistDao.insertPlaylistItem(
            PlaylistItemEntity(
                playlistId = playlistId,
                streamUrl = stream.url,
                title = stream.title,
                uploader = stream.uploader,
                thumbnailUrl = stream.thumbnailUrl
            )
        )
    }

    override suspend fun removeStreamFromPlaylist(itemId: Long) {
        playlistDao.deletePlaylistItem(itemId)
    }
}
