package com.wcp.tmdbcleanarchitecture.internal.di.modules

import android.app.Application
import android.content.Context
import com.wcp.data.platform.NetworkHandler
import com.wcp.tmdbcleanarchitecture.MovieApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideNetworkHandler(app: MovieApplication) = NetworkHandler(app)
}