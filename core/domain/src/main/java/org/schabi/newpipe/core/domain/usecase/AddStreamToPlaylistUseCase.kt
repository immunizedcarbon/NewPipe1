package org.schabi.newpipe.core.domain.usecase

import org.schabi.newpipe.core.data.repository.PlaylistRepository
import org.schabi.newpipe.core.model.Stream
import javax.inject.Inject

class AddStreamToPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(playlistId: Long, stream: Stream) =
        repository.addStreamToPlaylist(playlistId, stream)
}
