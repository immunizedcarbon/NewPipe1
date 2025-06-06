package org.schabi.newpipe.core.domain.usecase

import org.schabi.newpipe.core.data.repository.PlaylistRepository
import javax.inject.Inject

class CreatePlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(name: String) = repository.createPlaylist(name)
}
