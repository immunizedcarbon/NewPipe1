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
    private val dao: PlaylistDao
) : PlaylistRepository {
    override fun getPlaylists(): Flow<List<LocalPlaylist>> =
        dao.getPlaylists().map { list ->
            list.map { entity ->
                LocalPlaylist(
                    id = entity.id,
                    name = entity.name,
                    thumbnailUrl = entity.thumbnailUrl
                )
            }
        }

    override suspend fun createPlaylist(name: String, thumbnailUrl: String?): Long =
        dao.insertPlaylist(PlaylistEntity(name = name, thumbnailUrl = thumbnailUrl))

    override suspend fun deletePlaylist(id: Long) {
        dao.deletePlaylist(PlaylistEntity(id = id, name = "", thumbnailUrl = null))
    }

    override fun getPlaylistItems(playlistId: Long): Flow<List<Stream>> =
        dao.getItems(playlistId).map { list ->
            list.map { entity ->
                Stream(
                    url = entity.streamUrl,
                    channelUrl = null,
                    title = entity.title,
                    thumbnailUrl = entity.thumbnailUrl,
                    duration = 0L,
                    uploader = entity.uploader
                )
            }
        }

    override suspend fun addStreamToPlaylist(playlistId: Long, stream: Stream) {
        dao.insertItem(
            PlaylistItemEntity(
                playlistId = playlistId,
                streamUrl = stream.url,
                title = stream.title,
                thumbnailUrl = stream.thumbnailUrl,
                uploader = stream.uploader
            )
        )
    }

    override suspend fun removeStreamFromPlaylist(playlistId: Long, streamUrl: String) {
        dao.deleteItem(
            PlaylistItemEntity(
                id = 0,
                playlistId = playlistId,
                streamUrl = streamUrl,
                title = "",
                thumbnailUrl = null,
                uploader = null
            )
        )
    }
}
