package org.schabi.newpipe.feature.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.schabi.newpipe.core.data.preferences.PreferenceDataStore

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {
    private val prefs = PreferenceDataStore(context)
    val watchHistoryEnabled: StateFlow<Boolean> =
        prefs.isWatchHistoryEnabled.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)
    val theme: StateFlow<String> =
        prefs.theme.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "auto")
    val defaultResolution: StateFlow<String> =
        prefs.defaultResolution.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "720p60")

    fun setWatchHistoryEnabled(enabled: Boolean) {
        viewModelScope.launch { prefs.setWatchHistoryEnabled(enabled) }
    }
    fun setTheme(value: String) {
        viewModelScope.launch { prefs.setTheme(value) }
    }
    fun setDefaultResolution(value: String) {
        viewModelScope.launch { prefs.setDefaultResolution(value) }
    }
}
