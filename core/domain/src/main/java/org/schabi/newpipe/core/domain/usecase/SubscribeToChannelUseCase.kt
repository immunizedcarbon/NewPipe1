package org.schabi.newpipe.core.domain.usecase

import org.schabi.newpipe.core.data.repository.SubscriptionRepository
import org.schabi.newpipe.core.model.Channel
import javax.inject.Inject

class SubscribeToChannelUseCase @Inject constructor(
    private val repository: SubscriptionRepository
) {
    suspend operator fun invoke(channel: Channel) = repository.subscribe(channel)
}
