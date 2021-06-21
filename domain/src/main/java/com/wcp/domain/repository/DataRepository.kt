package com.wcp.domain.repository

import com.wcp.domain.results.MovieDetailResult
import com.wcp.domain.results.PopularResult
import com.wcp.domain.results.UpComingResult

interface DataRepository {
    suspend fun loadUpComingMovies(): UpComingResult
    suspend fun loaPopularMovies(): PopularResult
    suspend fun loadMovieDetails(movieId: String): MovieDetailResult
}