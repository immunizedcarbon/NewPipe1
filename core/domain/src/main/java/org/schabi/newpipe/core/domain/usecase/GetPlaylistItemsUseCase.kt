package org.schabi.newpipe.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.schabi.newpipe.core.data.repository.PlaylistRepository
import org.schabi.newpipe.core.model.Stream
import javax.inject.Inject

class GetPlaylistItemsUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    operator fun invoke(playlistId: Long): Flow<List<Stream>> =
        repository.getPlaylistItems(playlistId)
}
