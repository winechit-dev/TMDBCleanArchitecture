package com.wcp.data.repository

import com.wcp.data.dataSource.remoteDataSource.RemoteDataSource
import com.wcp.domain.repository.DataRepository
import com.wcp.domain.results.MovieDetailResult
import com.wcp.domain.results.PopularResult
import com.wcp.domain.results.UpComingResult
import javax.inject.Inject

class MovieDataRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : DataRepository {
    override suspend fun loadUpComingMovies(): UpComingResult {
        return dataSource.loadUpComingMovies()
    }

    override suspend fun loaPopularMovies(): PopularResult {
        return dataSource.loaPopularMovies()
    }

    override suspend fun loadMovieDetails(movieId: String): MovieDetailResult {
        return dataSource.loadMovieDetails(movieId = movieId)
    }

}