package org.schabi.newpipe.core.domain.usecase

import javax.inject.Inject
import org.schabi.newpipe.core.data.repository.PlaylistRepository

class CreatePlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(name: String, thumbnailUrl: String? = null) =
        repository.createPlaylist(name, thumbnailUrl)
}
