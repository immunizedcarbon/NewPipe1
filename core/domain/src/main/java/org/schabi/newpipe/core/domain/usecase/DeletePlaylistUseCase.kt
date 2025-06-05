package org.schabi.newpipe.core.domain.usecase

import javax.inject.Inject
import org.schabi.newpipe.core.data.repository.PlaylistRepository

class DeletePlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(id: Long) = repository.deletePlaylist(id)
}
