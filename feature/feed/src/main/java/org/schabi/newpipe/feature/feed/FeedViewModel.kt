package org.schabi.newpipe.feature.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.schabi.newpipe.core.domain.usecase.GetFeedUseCase
import org.schabi.newpipe.core.domain.usecase.RefreshFeedUseCase
import org.schabi.newpipe.core.model.Stream

@HiltViewModel
class FeedViewModel @Inject constructor(
    getFeedUseCase: GetFeedUseCase,
    private val refreshFeedUseCase: RefreshFeedUseCase
) : ViewModel() {
    val feed: StateFlow<List<Stream>> =
        getFeedUseCase()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            refreshFeedUseCase()
            _isRefreshing.value = false
        }
    }
}
