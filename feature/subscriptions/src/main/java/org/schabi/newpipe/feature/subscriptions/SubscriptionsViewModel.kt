package org.schabi.newpipe.feature.subscriptions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.schabi.newpipe.core.domain.usecase.GetSubscriptionsUseCase
import org.schabi.newpipe.core.model.Channel

@HiltViewModel
class SubscriptionsViewModel @Inject constructor(
    getSubscriptionsUseCase: GetSubscriptionsUseCase
) : ViewModel() {
    val subscriptions: StateFlow<List<Channel>> =
        getSubscriptionsUseCase()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
