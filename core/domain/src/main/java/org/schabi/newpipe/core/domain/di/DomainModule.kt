package org.schabi.newpipe.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.schabi.newpipe.core.data.repository.StreamRepository
import org.schabi.newpipe.core.domain.usecase.GetStreamDetailsUseCase

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideGetStreamDetailsUseCase(repository: StreamRepository): GetStreamDetailsUseCase =
        GetStreamDetailsUseCase(repository)
}
