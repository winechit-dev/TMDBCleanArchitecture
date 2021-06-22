package com.wcp.data.dataSource.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wcp.data.dataSource.localDataSource.favorites.FavoriteMovieData
import com.wcp.data.dataSource.localDataSource.favorites.FavoriteMovieDao

@Database(entities = [FavoriteMovieData::class], version = 1)
abstract class AppDatabase  : RoomDatabase(){
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}