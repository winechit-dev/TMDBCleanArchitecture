package com.wcp.domain.repository

import com.wcp.domain.models.FavoriteMovieDataModel
import com.wcp.domain.results.FavoriteMoviesDataResult
import com.wcp.domain.results.MovieDetailResult
import com.wcp.domain.results.PopularResult
import com.wcp.domain.results.UpComingResult

interface DataRepository {
    suspend fun loadUpComingMovies(): UpComingResult
    suspend fun loaPopularMovies(): PopularResult
    suspend fun loadMovieDetails(movieId: Int): MovieDetailResult

    suspend fun loadFavoriteMovies(): FavoriteMoviesDataResult
    suspend fun addFavoriteMovie(movie: FavoriteMovieDataModel): FavoriteMoviesDataResult
    suspend fun removeFavoriteMovie(id: String): FavoriteMoviesDataResult
}