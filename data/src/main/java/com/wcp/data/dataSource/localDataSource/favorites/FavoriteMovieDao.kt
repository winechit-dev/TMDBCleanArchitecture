package com.wcp.data.dataSource.localDataSource.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorite")
    suspend fun getFavoriteMovies(): List<FavoriteMovieData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteMovie(favoriteMovieData: FavoriteMovieData)

    @Query("DELETE FROM favorite WHERE id = :id")
    suspend fun deleteFavoriteMovieById(id: String): Int
}