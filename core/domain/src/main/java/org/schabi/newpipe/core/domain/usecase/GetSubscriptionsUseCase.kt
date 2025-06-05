package org.schabi.newpipe.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.schabi.newpipe.core.data.repository.SubscriptionRepository
import org.schabi.newpipe.core.model.Channel
import javax.inject.Inject

class GetSubscriptionsUseCase @Inject constructor(
    private val repository: SubscriptionRepository
) {
    operator fun invoke(): Flow<List<Channel>> = repository.getSubscriptions()
}
