package org.schabi.newpipe.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.schabi.newpipe.core.data.preferences.PreferenceDataStore
import org.schabi.newpipe.core.data.repository.HistoryRepository

data class SettingsUiState(
    val watchHistoryEnabled: Boolean = false,
    val selectedTheme: String = "system",
    val defaultResolution: String = "best"
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val prefs: PreferenceDataStore,
    private val historyRepository: HistoryRepository
) : ViewModel() {

    val uiState: StateFlow<SettingsUiState> =
        prefs.isWatchHistoryEnabled.map { enabled ->
            SettingsUiState(
                watchHistoryEnabled = enabled,
                selectedTheme = "system",
                defaultResolution = "best"
            )
        }.stateIn(viewModelScope, SharingStarted.Eagerly, SettingsUiState())

    fun setWatchHistoryEnabled(enabled: Boolean) {
        viewModelScope.launch { historyRepository.setWatchHistoryEnabled(enabled) }
    }

    fun setTheme(theme: String) {
        viewModelScope.launch { prefs.setSelectedTheme(theme) }
    }

    fun setDefaultResolution(resolution: String) {
        viewModelScope.launch { prefs.setDefaultResolution(resolution) }
    }
}
