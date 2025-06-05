package org.schabi.newpipe.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.schabi.newpipe.core.domain.usecase.GetSearchResultsUseCase
import org.schabi.newpipe.core.model.Stream

data class SearchUiState(
    val query: String = "",
    val results: List<Stream> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResults: GetSearchResultsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun onQueryChanged(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
    }

    fun executeSearch() {
        viewModelScope.launch {
            val query = _uiState.value.query
            if (query.isBlank()) return@launch
            _uiState.update { it.copy(isLoading = true) }
            getSearchResults(query).collect { result ->
                result.onSuccess { streams ->
                    _uiState.update { it.copy(results = streams, isLoading = false) }
                }.onFailure {
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }
}
