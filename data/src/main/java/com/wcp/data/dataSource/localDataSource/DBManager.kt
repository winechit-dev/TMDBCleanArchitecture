package com.wcp.data.dataSource.localDataSource

import com.wcp.domain.models.FavoriteMovieDataModel
import com.wcp.domain.results.FavoriteMoviesDataResult

interface DBManager {
    suspend fun loadFavoriteMovies(): FavoriteMoviesDataResult
    suspend fun addFavoriteMovie(favoriteMovie : FavoriteMovieDataModel) : FavoriteMoviesDataResult
    suspend fun removeFavoriteMovie(id : String) : FavoriteMoviesDataResult
}