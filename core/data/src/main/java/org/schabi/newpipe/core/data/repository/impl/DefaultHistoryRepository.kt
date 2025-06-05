package org.schabi.newpipe.core.data.repository.impl

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.schabi.newpipe.core.data.db.HistoryDao
import org.schabi.newpipe.core.data.db.HistoryEntryEntity
import org.schabi.newpipe.core.data.preferences.PreferenceDataStore
import org.schabi.newpipe.core.data.repository.HistoryRepository
import org.schabi.newpipe.core.model.Stream

class DefaultHistoryRepository @Inject constructor(
    @ApplicationContext context: Context,
    private val historyDao: HistoryDao
) : HistoryRepository {
    private val prefs = PreferenceDataStore(context)
    override val isWatchHistoryEnabled: Flow<Boolean> = prefs.isWatchHistoryEnabled

    override fun getWatchHistory(): Flow<List<Stream>> =
        historyDao.getHistory().map { list ->
            list.map { entity ->
                Stream(
                    url = entity.streamUrl,
                    title = entity.title,
                    thumbnailUrl = entity.thumbnailUrl,
                    duration = 0L,
                    uploader = entity.uploader
                )
            }
        }

    override suspend fun addStreamToHistory(stream: Stream) {
        historyDao.insert(
            HistoryEntryEntity(
                streamUrl = stream.url,
                title = stream.title,
                uploader = stream.uploader ?: "",
                thumbnailUrl = stream.thumbnailUrl,
                accessDate = System.currentTimeMillis()
            )
        )
    }

    override suspend fun setWatchHistoryEnabled(enabled: Boolean) =
        prefs.setWatchHistoryEnabled(enabled)
}
