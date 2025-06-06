package org.schabi.newpipe.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.schabi.newpipe.core.data.repository.FeedRepository
import org.schabi.newpipe.core.model.Stream
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val repository: FeedRepository
) {
    operator fun invoke(): Flow<List<Stream>> = repository.getFeed()
}
