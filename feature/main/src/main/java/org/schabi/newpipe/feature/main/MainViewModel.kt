package org.schabi.newpipe.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.schabi.newpipe.core.domain.usecase.GetTrendingStreamsUseCase
import org.schabi.newpipe.core.model.Stream

data class MainScreenUiState(
    val trendingStreams: List<Stream> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTrendingStreams: GetTrendingStreamsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getTrendingStreams().collect { result ->
                result.onSuccess { streams ->
                    _uiState.update { it.copy(trendingStreams = streams, isLoading = false) }
                }.onFailure {
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }
}
