package org.schabi.newpipe.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.schabi.newpipe.core.data.repository.HistoryRepository
import org.schabi.newpipe.core.model.Stream
import javax.inject.Inject

class GetWatchHistoryUseCase @Inject constructor(
    private val repository: HistoryRepository
) {
    operator fun invoke(): Flow<List<Stream>> = repository.getWatchHistory()
}
