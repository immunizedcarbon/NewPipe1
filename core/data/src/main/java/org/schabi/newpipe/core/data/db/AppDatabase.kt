package org.schabi.newpipe.core.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(exportSchema = false,
    entities = [
        HistoryEntryEntity::class,
        SubscriptionEntity::class,
        PlaylistEntity::class,
        PlaylistItemEntity::class,
        FeedItemEntity::class,
        DownloadEntity::class
    ],
    version = 5,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = AppDatabase.Migration1To2::class),
        AutoMigration(from = 2, to = 3, spec = AppDatabase.Migration2To3::class),
        AutoMigration(from = 3, to = 4, spec = AppDatabase.Migration3To4::class),
        AutoMigration(from = 4, to = 5, spec = AppDatabase.Migration4To5::class)
    ],
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
