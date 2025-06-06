package org.schabi.newpipe.feature.downloads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.schabi.newpipe.core.domain.usecase.GetDownloadsUseCase
import org.schabi.newpipe.core.model.Download

@HiltViewModel
class DownloadsViewModel @Inject constructor(
    getDownloads: GetDownloadsUseCase
) : ViewModel() {
    val downloads: StateFlow<List<Download>> =
        getDownloads().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
