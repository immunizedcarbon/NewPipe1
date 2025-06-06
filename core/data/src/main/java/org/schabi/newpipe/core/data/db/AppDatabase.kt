package org.schabi.newpipe.core.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [
        HistoryEntryEntity::class,
        SubscriptionEntity::class,
        PlaylistEntity::class,
        PlaylistItemEntity::class
    ],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = AppDatabase.Migration1To2::class),
        AutoMigration(from = 2, to = 3, spec = AppDatabase.Migration2To3::class)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun subscriptionDao(): SubscriptionDao
    abstract fun playlistDao(): PlaylistDao

    class Migration1To2 : AutoMigrationSpec
    class Migration2To3 : AutoMigrationSpec
}
