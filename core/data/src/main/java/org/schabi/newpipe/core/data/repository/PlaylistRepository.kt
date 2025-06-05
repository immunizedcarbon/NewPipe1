package org.schabi.newpipe.core.data.repository

import kotlinx.coroutines.flow.Flow
import org.schabi.newpipe.core.model.LocalPlaylist
import org.schabi.newpipe.core.model.Stream

interface PlaylistRepository {
    fun getPlaylists(): Flow<List<LocalPlaylist>>
    suspend fun createPlaylist(name: String, thumbnailUrl: String? = null)
    suspend fun deletePlaylist(id: Long)
    fun getPlaylistItems(playlistId: Long): Flow<List<Stream>>
    suspend fun addStreamToPlaylist(playlistId: Long, stream: Stream)
    suspend fun removeStreamFromPlaylist(itemId: Long)
}
