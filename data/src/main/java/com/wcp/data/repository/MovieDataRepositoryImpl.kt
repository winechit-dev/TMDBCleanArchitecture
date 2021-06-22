package com.wcp.data.repository

import com.wcp.data.dataSource.localDataSource.DBManager
import com.wcp.data.dataSource.remoteDataSource.RemoteDataSource
import com.wcp.domain.models.FavoriteMovieDataModel
import com.wcp.domain.repository.DataRepository
import com.wcp.domain.results.FavoriteMoviesDataResult
import com.wcp.domain.results.MovieDetailResult
import com.wcp.domain.results.PopularResult
import com.wcp.domain.results.UpComingResult
import javax.inject.Inject

class MovieDataRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource,
    private val dbManager: DBManager
) : DataRepository {
    override suspend fun loadUpComingMovies(): UpComingResult {
        return dataSource.loadUpComingMovies()
    }

    override suspend fun loaPopularMovies(): PopularResult {
        return dataSource.loaPopularMovies()
    }

    override suspend fun loadMovieDetails(movieId: Int): MovieDetailResult {
        return dataSource.loadMovieDetails(movieId = movieId)
    }

    override suspend fun loadFavoriteMovies(): FavoriteMoviesDataResult {
        return dbManager.loadFavoriteMovies()
    }

    override suspend fun addFavoriteMovie(movie: FavoriteMovieDataModel): FavoriteMoviesDataResult {
        return dbManager.addFavoriteMovie(movie)
    }

    override suspend fun removeFavoriteMovie(id: String): FavoriteMoviesDataResult {
        return dbManager.removeFavoriteMovie(id)
    }

}