package org.schabi.newpipe.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.schabi.newpipe.core.data.repository.StreamRepository
import org.schabi.newpipe.core.data.repository.HistoryRepository
import org.schabi.newpipe.core.domain.usecase.AddStreamToHistoryUseCase
import org.schabi.newpipe.core.domain.usecase.GetSearchResultsUseCase
import org.schabi.newpipe.core.domain.usecase.GetStreamDetailsUseCase
import org.schabi.newpipe.core.domain.usecase.GetTrendingStreamsUseCase
import org.schabi.newpipe.core.domain.usecase.GetWatchHistoryUseCase

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
}
