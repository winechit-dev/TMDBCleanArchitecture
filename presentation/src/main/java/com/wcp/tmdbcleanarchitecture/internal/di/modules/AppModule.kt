package com.wcp.tmdbcleanarchitecture.internal.di.modules
import android.content.Context
import androidx.room.Room
import com.wcp.data.dataSource.localDataSource.AppDatabase
import com.wcp.data.dataSource.localDataSource.DBManager
import com.wcp.data.dataSource.localDataSource.DBManagerImpl
import com.wcp.data.platform.NetworkHandler
import com.wcp.tmdbcleanarchitecture.MovieApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun getContext(): Context {
        return MovieApplication.get().applicationContext
    }

    @Provides
    fun provideNetworkHandler(app: MovieApplication) = NetworkHandler(app)


    @Provides
    fun providesDBManager(context: Context): DBManager {
        val appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "movie-database")
            .build()
        return DBManagerImpl(appDatabase)
    }

}