package org.schabi.newpipe.core.data.repository.impl

import org.schabi.newpipe.core.data.repository.PlaylistRepository
import org.schabi.newpipe.core.model.Playlist
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.playlist.PlaylistInfo

class DefaultPlaylistRepository : PlaylistRepository {
    override suspend fun getPlaylist(url: String): Playlist {
        val service = NewPipe.getServiceByUrl(url)
        val info = PlaylistInfo.getInfo(service, url)
        return Playlist(
            url = info.url,
            name = info.name,
            thumbnailUrl = info.avatarUrl
        )
    }
}
