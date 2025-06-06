package org.schabi.newpipe.core.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import org.schabi.newpipe.core.data.db.AppDatabase
import org.schabi.newpipe.core.data.db.HistoryDao
import org.schabi.newpipe.core.data.db.SubscriptionDao
import org.schabi.newpipe.core.data.db.PlaylistDao
import org.schabi.newpipe.core.data.db.FeedDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "newpipe.db").build()

    @Provides
    fun provideHistoryDao(db: AppDatabase): HistoryDao = db.historyDao()

    @Provides
    fun provideSubscriptionDao(db: AppDatabase): SubscriptionDao = db.subscriptionDao()

    @Provides
    fun providePlaylistDao(db: AppDatabase): PlaylistDao = db.playlistDao()

    @Provides
    fun provideFeedDao(db: AppDatabase): FeedDao = db.feedDao()
}
