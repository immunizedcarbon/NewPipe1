package org.schabi.newpipe.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [
        HistoryEntryEntity::class,
        SubscriptionEntity::class,
        PlaylistEntity::class,
        PlaylistItemEntity::class,
        FeedItemEntity::class,
        DownloadEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun subscriptionDao(): SubscriptionDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun feedDao(): FeedDao
    abstract fun downloadDao(): DownloadDao

    class Migration1To2 : AutoMigrationSpec
    class Migration2To3 : AutoMigrationSpec
    class Migration3To4 : AutoMigrationSpec
    class Migration4To5 : AutoMigrationSpec
}
