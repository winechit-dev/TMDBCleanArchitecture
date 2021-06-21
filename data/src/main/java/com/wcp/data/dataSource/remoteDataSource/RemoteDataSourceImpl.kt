package com.wcp.data.dataSource.remoteDataSource

import com.wcp.data.platform.NetworkHandler
import com.wcp.data.services.MovieService
import com.wcp.domain.results.MovieDetailResult
import com.wcp.domain.results.PopularResult
import com.wcp.domain.results.UpComingResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: MovieService,
    private val apiKey: String
) : RemoteDataSource {
    override suspend fun loadUpComingMovies(): UpComingResult {
        return withContext(Dispatchers.IO) {
            if (networkHandler.isConnected) {
                val response = try {
                    service.loadUpComingMovies(apiKey)
                } catch (e: Exception) {
                    return@withContext UpComingResult.ServerError(e.localizedMessage ?: e.message!!)
                }
                if (response != null) {
                    return@withContext UpComingResult.Success(response)
                } else {
                    return@withContext UpComingResult.FeatureFailure
                }

            } else {
                return@withContext UpComingResult.NetworkConnectionFailure
            }
        }
    }

    override suspend fun loaPopularMovies(): PopularResult {
        return withContext(Dispatchers.IO) {
            if (networkHandler.isConnected) {
                val response = try {
                    service.loaPopularMovies(apiKey)
                } catch (e: Exception) {
                    return@withContext PopularResult.ServerError(e.localizedMessage ?: e.message!!)
                }
                if (response != null) {
                    return@withContext PopularResult.Success(response)
                } else {
                    return@withContext PopularResult.FeatureFailure
                }

            } else {
                return@withContext PopularResult.NetworkConnectionFailure
            }
        }
    }

    override suspend fun loadMovieDetails(movieId: String): MovieDetailResult {
        return withContext(Dispatchers.IO) {
            if (networkHandler.isConnected) {
                val response = try {
                    service.loadMovieDetails(movieId = movieId, apiKey = apiKey)
                } catch (e: Exception) {
                    return@withContext MovieDetailResult.ServerError(
                        e.localizedMessage ?: e.message!!
                    )
                }
                if (response != null) {
                    return@withContext MovieDetailResult.Success(response)
                } else {
                    return@withContext MovieDetailResult.FeatureFailure
                }

            } else {
                return@withContext MovieDetailResult.NetworkConnectionFailure
            }
        }
    }
}