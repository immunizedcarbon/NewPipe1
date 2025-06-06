package org.schabi.newpipe.core.domain.usecase

import org.schabi.newpipe.core.data.repository.FeedRepository
import javax.inject.Inject

class RefreshFeedUseCase @Inject constructor(
    private val repository: FeedRepository
) {
    suspend operator fun invoke() = repository.refreshFeed()
}
