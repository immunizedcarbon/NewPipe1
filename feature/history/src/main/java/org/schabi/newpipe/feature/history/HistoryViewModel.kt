package org.schabi.newpipe.feature.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.schabi.newpipe.core.domain.usecase.GetWatchHistoryUseCase
import org.schabi.newpipe.core.model.Stream
import kotlinx.coroutines.launch

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getWatchHistoryUseCase: GetWatchHistoryUseCase
) : ViewModel() {
    private val _history = MutableStateFlow<List<Stream>>(emptyList())
    val history: StateFlow<List<Stream>> = _history.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            getWatchHistoryUseCase().collect { list ->
                _history.value = list
                _isLoading.value = false
            }
        }
    }
}

