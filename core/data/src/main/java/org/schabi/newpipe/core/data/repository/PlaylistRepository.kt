package org.schabi.newpipe.core.data.repository

import org.schabi.newpipe.core.model.Playlist

interface PlaylistRepository {
    suspend fun getPlaylist(url: String): Playlist
}
