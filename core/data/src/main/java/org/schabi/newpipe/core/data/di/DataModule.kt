package org.schabi.newpipe.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.schabi.newpipe.core.data.repository.HistoryRepository
import org.schabi.newpipe.core.data.repository.PlaylistRepository
import org.schabi.newpipe.core.data.repository.FeedRepository
import org.schabi.newpipe.core.data.repository.StreamRepository
import org.schabi.newpipe.core.data.repository.SubscriptionRepository
import org.schabi.newpipe.core.data.repository.impl.DefaultHistoryRepository
import org.schabi.newpipe.core.data.repository.impl.DefaultPlaylistRepository
import org.schabi.newpipe.core.data.repository.impl.DefaultStreamRepository
import org.schabi.newpipe.core.data.repository.impl.DefaultSubscriptionRepository
import org.schabi.newpipe.core.data.repository.impl.DefaultFeedRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindStreamRepository(impl: DefaultStreamRepository): StreamRepository

    @Binds
    abstract fun bindPlaylistRepository(impl: DefaultPlaylistRepository): PlaylistRepository

    @Binds
    abstract fun bindHistoryRepository(impl: DefaultHistoryRepository): HistoryRepository

    @Binds
    abstract fun bindSubscriptionRepository(impl: DefaultSubscriptionRepository): SubscriptionRepository

    @Binds
    abstract fun bindFeedRepository(impl: DefaultFeedRepository): FeedRepository
}
