package org.schabi.newpipe.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.schabi.newpipe.core.data.repository.StreamRepository
import org.schabi.newpipe.core.data.repository.HistoryRepository
import org.schabi.newpipe.core.data.repository.SubscriptionRepository
import org.schabi.newpipe.core.data.repository.PlaylistRepository
import org.schabi.newpipe.core.data.repository.FeedRepository
import org.schabi.newpipe.core.data.repository.DownloadRepository
import org.schabi.newpipe.core.domain.usecase.AddStreamToHistoryUseCase
import org.schabi.newpipe.core.domain.usecase.GetSearchResultsUseCase
import org.schabi.newpipe.core.domain.usecase.GetStreamDetailsUseCase
import org.schabi.newpipe.core.domain.usecase.GetTrendingStreamsUseCase
import org.schabi.newpipe.core.domain.usecase.GetChannelStreamsUseCase
import org.schabi.newpipe.core.domain.usecase.GetWatchHistoryUseCase
import org.schabi.newpipe.core.domain.usecase.SubscribeToChannelUseCase
import org.schabi.newpipe.core.domain.usecase.GetSubscriptionsUseCase
import org.schabi.newpipe.core.domain.usecase.UnsubscribeFromChannelUseCase
import org.schabi.newpipe.core.domain.usecase.CreatePlaylistUseCase
import org.schabi.newpipe.core.domain.usecase.GetPlaylistsUseCase
import org.schabi.newpipe.core.domain.usecase.AddStreamToPlaylistUseCase
import org.schabi.newpipe.core.domain.usecase.GetPlaylistItemsUseCase
import org.schabi.newpipe.core.domain.usecase.RemoveStreamFromPlaylistUseCase
import org.schabi.newpipe.core.domain.usecase.DeletePlaylistUseCase
import org.schabi.newpipe.core.domain.usecase.GetFeedUseCase
import org.schabi.newpipe.core.domain.usecase.RefreshFeedUseCase
import org.schabi.newpipe.core.domain.usecase.GetDownloadsUseCase

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideGetStreamDetailsUseCase(repository: StreamRepository): GetStreamDetailsUseCase =
        GetStreamDetailsUseCase(repository)

    @Provides
    fun provideGetTrendingStreamsUseCase(repository: StreamRepository): GetTrendingStreamsUseCase =
        GetTrendingStreamsUseCase(repository)

    @Provides
    fun provideGetChannelStreamsUseCase(repository: StreamRepository): GetChannelStreamsUseCase =
        GetChannelStreamsUseCase(repository)

    @Provides
    fun provideGetWatchHistoryUseCase(repository: HistoryRepository): GetWatchHistoryUseCase =
        GetWatchHistoryUseCase(repository)

    @Provides
    fun provideGetSearchResultsUseCase(repository: StreamRepository): GetSearchResultsUseCase =
        GetSearchResultsUseCase(repository)

    @Provides
    fun provideAddStreamToHistoryUseCase(repository: HistoryRepository): AddStreamToHistoryUseCase =
        AddStreamToHistoryUseCase(repository)

    @Provides
    fun provideSubscribeToChannelUseCase(repository: SubscriptionRepository): SubscribeToChannelUseCase =
        SubscribeToChannelUseCase(repository)

    @Provides
    fun provideGetSubscriptionsUseCase(repository: SubscriptionRepository): GetSubscriptionsUseCase =
        GetSubscriptionsUseCase(repository)

    @Provides
    fun provideUnsubscribeFromChannelUseCase(repository: SubscriptionRepository): UnsubscribeFromChannelUseCase =
        UnsubscribeFromChannelUseCase(repository)

    @Provides
    fun provideCreatePlaylistUseCase(repository: PlaylistRepository): CreatePlaylistUseCase =
        CreatePlaylistUseCase(repository)

    @Provides
    fun provideGetPlaylistsUseCase(repository: PlaylistRepository): GetPlaylistsUseCase =
        GetPlaylistsUseCase(repository)

    @Provides
    fun provideAddStreamToPlaylistUseCase(repository: PlaylistRepository): AddStreamToPlaylistUseCase =
        AddStreamToPlaylistUseCase(repository)

    @Provides
    fun provideGetPlaylistItemsUseCase(repository: PlaylistRepository): GetPlaylistItemsUseCase =
        GetPlaylistItemsUseCase(repository)

    @Provides
    fun provideRemoveStreamFromPlaylistUseCase(repository: PlaylistRepository): RemoveStreamFromPlaylistUseCase =
        RemoveStreamFromPlaylistUseCase(repository)

    @Provides
    fun provideDeletePlaylistUseCase(repository: PlaylistRepository): DeletePlaylistUseCase =
        DeletePlaylistUseCase(repository)

    @Provides
    fun provideGetFeedUseCase(repository: FeedRepository): GetFeedUseCase =
        GetFeedUseCase(repository)

    @Provides
    fun provideRefreshFeedUseCase(repository: FeedRepository): RefreshFeedUseCase =
        RefreshFeedUseCase(repository)

    @Provides
    fun provideGetDownloadsUseCase(repository: DownloadRepository): GetDownloadsUseCase =
        GetDownloadsUseCase(repository)
}
