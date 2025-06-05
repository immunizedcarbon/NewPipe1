package org.schabi.newpipe.core.domain.usecase

import org.schabi.newpipe.core.data.repository.HistoryRepository
import org.schabi.newpipe.core.model.Stream
import javax.inject.Inject

class AddStreamToHistoryUseCase @Inject constructor(
    private val repository: HistoryRepository
) {
    suspend operator fun invoke(stream: Stream) = repository.addStreamToHistory(stream)
}
