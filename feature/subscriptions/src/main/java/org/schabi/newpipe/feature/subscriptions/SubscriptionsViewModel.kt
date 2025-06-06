package org.schabi.newpipe.feature.subscriptions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.schabi.newpipe.core.domain.usecase.GetSubscriptionsUseCase
import org.schabi.newpipe.core.model.Channel
import kotlinx.coroutines.launch

@HiltViewModel
class SubscriptionsViewModel @Inject constructor(
    private val getSubscriptionsUseCase: GetSubscriptionsUseCase
) : ViewModel() {
    private val _subscriptions = MutableStateFlow<List<Channel>>(emptyList())
    val subscriptions: StateFlow<List<Channel>> = _subscriptions.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            getSubscriptionsUseCase().collect { list ->
                _subscriptions.value = list
                _isLoading.value = false
            }
        }
    }
}

