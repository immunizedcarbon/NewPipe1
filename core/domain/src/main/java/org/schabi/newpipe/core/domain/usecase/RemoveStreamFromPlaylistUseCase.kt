package org.schabi.newpipe.core.domain.usecase

import org.schabi.newpipe.core.data.repository.PlaylistRepository
import javax.inject.Inject

class RemoveStreamFromPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(playlistId: Long, streamUrl: String) =
        repository.removeStreamFromPlaylist(playlistId, streamUrl)
}
