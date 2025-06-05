package org.schabi.newpipe.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.schabi.newpipe.core.data.repository.StreamRepository
import org.schabi.newpipe.core.data.repository.HistoryRepository
import org.schabi.newpipe.core.data.repository.PlaylistRepository
import org.schabi.newpipe.core.domain.usecase.AddStreamToHistoryUseCase
import org.schabi.newpipe.core.domain.usecase.GetSearchResultsUseCase
import org.schabi.newpipe.core.domain.usecase.GetStreamDetailsUseCase
import org.schabi.newpipe.core.domain.usecase.GetTrendingStreamsUseCase
import org.schabi.newpipe.core.domain.usecase.GetWatchHistoryUseCase
import org.schabi.newpipe.core.domain.usecase.CreatePlaylistUseCase
import org.schabi.newpipe.core.domain.usecase.GetPlaylistsUseCase
import org.schabi.newpipe.core.domain.usecase.AddStreamToPlaylistUseCase
import org.schabi.newpipe.core.domain.usecase.DeletePlaylistUseCase
import org.schabi.newpipe.core.domain.usecase.GetPlaylistItemsUseCase

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
    fun provideGetWatchHistoryUseCase(repository: HistoryRepository): GetWatchHistoryUseCase =
        GetWatchHistoryUseCase(repository)

    @Provides
    fun provideGetSearchResultsUseCase(repository: StreamRepository): GetSearchResultsUseCase =
        GetSearchResultsUseCase(repository)

    @Provides
    fun provideAddStreamToHistoryUseCase(repository: HistoryRepository): AddStreamToHistoryUseCase =
        AddStreamToHistoryUseCase(repository)

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
    fun provideDeletePlaylistUseCase(repository: PlaylistRepository): DeletePlaylistUseCase =
        DeletePlaylistUseCase(repository)

    @Provides
    fun provideGetPlaylistItemsUseCase(repository: PlaylistRepository): GetPlaylistItemsUseCase =
        GetPlaylistItemsUseCase(repository)
}
