package org.schabi.newpipe.feature.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.schabi.newpipe.core.domain.usecase.GetFeedUseCase
import org.schabi.newpipe.core.domain.usecase.RefreshFeedUseCase
import org.schabi.newpipe.core.model.Stream

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeedUseCase: GetFeedUseCase,
    private val refreshFeedUseCase: RefreshFeedUseCase
) : ViewModel() {
    private val _feed = MutableStateFlow<List<Stream>>(emptyList())
    val feed: StateFlow<List<Stream>> = _feed.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        viewModelScope.launch {
            getFeedUseCase().collect { list ->
                _feed.value = list
                _isLoading.value = false
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            refreshFeedUseCase()
            _isRefreshing.value = false
        }
    }
}

