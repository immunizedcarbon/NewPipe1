package org.schabi.newpipe.core.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import org.schabi.newpipe.core.data.repository.PlaylistRepository
import org.schabi.newpipe.core.model.LocalPlaylist

class GetPlaylistsUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    operator fun invoke(): Flow<List<LocalPlaylist>> = repository.getPlaylists()
}
