package org.schabi.newpipe.core.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [HistoryEntryEntity::class, SubscriptionEntity::class],
    version = 2,
    autoMigrations = [AutoMigration(from = 1, to = 2, spec = AppDatabase.Migration1To2::class)]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun subscriptionDao(): SubscriptionDao

    class Migration1To2 : AutoMigrationSpec
}
