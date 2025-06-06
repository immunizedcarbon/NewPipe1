package org.schabi.newpipe.core.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceDataStore(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "settings")

    private val watchHistoryKey = booleanPreferencesKey("enable_watch_history")
    private val themeKey = stringPreferencesKey("theme")
    private val resolutionKey = stringPreferencesKey("default_resolution")
    private val showHigherResolutionsKey = booleanPreferencesKey("show_higher_resolutions")

    val isWatchHistoryEnabled: Flow<Boolean> =
        context.dataStore.data.map { it[watchHistoryKey] ?: false }
    val theme: Flow<String> =
        context.dataStore.data.map { it[themeKey] ?: "auto" }
    val defaultResolution: Flow<String> =
        context.dataStore.data.map { it[resolutionKey] ?: "720p60" }
    val showHigherResolutions: Flow<Boolean> =
        context.dataStore.data.map { it[showHigherResolutionsKey] ?: false }

    suspend fun setWatchHistoryEnabled(enabled: Boolean) {
        context.dataStore.edit { it[watchHistoryKey] = enabled }
    }
    suspend fun setTheme(value: String) {
        context.dataStore.edit { it[themeKey] = value }
    }
    suspend fun setDefaultResolution(value: String) {
        context.dataStore.edit { it[resolutionKey] = value }
    }
    suspend fun setShowHigherResolutions(value: Boolean) {
        context.dataStore.edit { it[showHigherResolutionsKey] = value }
    }
}
