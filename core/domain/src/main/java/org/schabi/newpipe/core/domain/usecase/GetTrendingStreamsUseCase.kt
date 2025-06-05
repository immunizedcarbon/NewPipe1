package org.schabi.newpipe.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.schabi.newpipe.core.data.repository.StreamRepository
import org.schabi.newpipe.core.model.Stream
import javax.inject.Inject

class GetTrendingStreamsUseCase @Inject constructor(
    private val repository: StreamRepository
) {
    suspend operator fun invoke(): Flow<Result<List<Stream>>> = flow {
        emit(runCatching { repository.getTrending() })
    }
}
