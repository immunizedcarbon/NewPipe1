package org.schabi.newpipe.core.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceDataStore(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "settings")

    private val watchHistoryKey = booleanPreferencesKey("enable_watch_history")
    private val themeKey = stringPreferencesKey("selected_theme")
    private val resolutionKey = stringPreferencesKey("default_resolution")

    val isWatchHistoryEnabled: Flow<Boolean> =
        context.dataStore.data.map { it[watchHistoryKey] ?: false }

    val selectedTheme: Flow<String> =
        context.dataStore.data.map { it[themeKey] ?: "system" }

    val defaultResolution: Flow<String> =
        context.dataStore.data.map { it[resolutionKey] ?: "best" }

    suspend fun setWatchHistoryEnabled(enabled: Boolean) {
        context.dataStore.edit { it[watchHistoryKey] = enabled }
    }

    suspend fun setSelectedTheme(theme: String) {
        context.dataStore.edit { it[themeKey] = theme }
    }

    suspend fun setDefaultResolution(resolution: String) {
        context.dataStore.edit { it[resolutionKey] = resolution }
    }
}
