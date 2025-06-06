package org.schabi.newpipe.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.schabi.newpipe.core.data.repository.StreamRepository
import org.schabi.newpipe.core.model.Stream
import javax.inject.Inject

class GetChannelStreamsUseCase @Inject constructor(
    private val repository: StreamRepository
) {
    suspend operator fun invoke(channelUrl: String): Flow<Result<List<Stream>>> = flow {
        emit(runCatching { repository.getStreamsForChannel(channelUrl) })
    }
}
