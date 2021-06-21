package com.wcp.tmdbcleanarchitecture.internal.di.modules

import com.wcp.data.repository.MovieDataRepositoryImpl
import com.wcp.domain.repository.DataRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideDataRepository(movieDataRepositoryImpl: MovieDataRepositoryImpl): DataRepository = movieDataRepositoryImpl
}