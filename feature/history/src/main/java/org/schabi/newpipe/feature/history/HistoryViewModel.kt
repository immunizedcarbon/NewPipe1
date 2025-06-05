package org.schabi.newpipe.feature.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.schabi.newpipe.core.domain.usecase.GetWatchHistoryUseCase
import org.schabi.newpipe.core.model.Stream

@HiltViewModel
class HistoryViewModel @Inject constructor(
    getWatchHistoryUseCase: GetWatchHistoryUseCase
) : ViewModel() {
    val history: StateFlow<List<Stream>> =
        getWatchHistoryUseCase()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
