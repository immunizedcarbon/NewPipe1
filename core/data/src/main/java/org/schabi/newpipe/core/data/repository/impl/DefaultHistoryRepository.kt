package org.schabi.newpipe.core.data.repository.impl

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.schabi.newpipe.core.data.preferences.PreferenceDataStore
import org.schabi.newpipe.core.data.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultHistoryRepository @Inject constructor(@ApplicationContext context: Context) : HistoryRepository {
    private val prefs = PreferenceDataStore(context)
    override val isWatchHistoryEnabled: Flow<Boolean> = prefs.isWatchHistoryEnabled
    override suspend fun setWatchHistoryEnabled(enabled: Boolean) = prefs.setWatchHistoryEnabled(enabled)
}
