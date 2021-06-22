package com.wcp.data.dataSource.localDataSource

import com.wcp.domain.results.MovieDetailResult
import com.wcp.domain.results.PopularResult
import com.wcp.domain.results.UpComingResult

interface LocalDataSource {
    suspend fun loadUpComingMovies(): UpComingResult
    suspend fun loaPopularMovies(): PopularResult
    suspend fun loadMovieDetails(movieId: Int): MovieDetailResult
}