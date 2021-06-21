package com.wcp.data.services

import com.wcp.domain.models.MoviesResponseModel
import com.wcp.domain.models.PopularDataModel
import com.wcp.domain.models.UpComingDataModel
import com.wcp.domain.models.detail.MovieDetailDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("upcoming")
    suspend fun loadUpComingMovies(
        @Query("api_key") apiKey: String
    ): MoviesResponseModel<UpComingDataModel>?


    @GET("popular")
    suspend fun loaPopularMovies(
        @Query("api_key") apiKey: String
    ): MoviesResponseModel<PopularDataModel>?


    @GET("{movie_id}")
    suspend fun loadMovieDetails(
        @Path("movie_id") movieId : String,
        @Query("api_key") apiKey : String
    ): MoviesResponseModel<MovieDetailDataModel>?

}