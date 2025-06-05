package org.schabi.newpipe.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
