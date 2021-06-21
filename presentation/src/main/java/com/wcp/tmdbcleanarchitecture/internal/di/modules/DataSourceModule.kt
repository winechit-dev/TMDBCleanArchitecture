package com.wcp.tmdbcleanarchitecture.internal.di.modules

import com.wcp.data.BuildConfig
import com.wcp.data.dataSource.remoteDataSource.RemoteDataSource
import com.wcp.data.dataSource.remoteDataSource.RemoteDataSourceImpl
import com.wcp.data.platform.NetworkHandler
import com.wcp.data.services.MovieService
import dagger.Module
import dagger.Provides


@Module
class DataSourceModule {

    @Provides
    fun provideDataSource(
        networkHandler: NetworkHandler,
        service: MovieService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            networkHandler = networkHandler,
            service = service,
            apiKey = BuildConfig.API_KEY
        )
    }
}