package com.wcp.data.dataSource.localDataSource

import com.wcp.data.dataSource.localDataSource.favorites.FavoriteMovieData
import com.wcp.data.mapper.FavoriteMoviesDataMapper
import com.wcp.domain.models.FavoriteMovieDataModel
import com.wcp.domain.results.FavoriteMoviesDataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DBManagerImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : DBManager {
    private val mapper = FavoriteMoviesDataMapper()
    override suspend fun loadFavoriteMovies(): FavoriteMoviesDataResult {
        return withContext(Dispatchers.IO) {
            try {
                FavoriteMoviesDataResult.Success(
                    mapper.transform(
                        appDatabase.favoriteMovieDao().getFavoriteMovies()
                    )
                )
            } catch (e: Exception) {
                FavoriteMoviesDataResult.Failure
            }
        }

    }

    override suspend fun addFavoriteMovie(favoriteMovie: FavoriteMovieDataModel): FavoriteMoviesDataResult {
        return withContext(Dispatchers.IO) {
            try {
                appDatabase.favoriteMovieDao().addFavoriteMovie(
                    FavoriteMovieData(
                        id = favoriteMovie.id,
                        poster_path = favoriteMovie.poster_path,
                        release_date = favoriteMovie.release_date ?: "0000-00-00",
                        title = favoriteMovie.title,
                        vote_average = favoriteMovie.vote_average,
                        vote_count = favoriteMovie.vote_count
                    )
                )
                FavoriteMoviesDataResult.SuccessAddFavoriteMovie
            } catch (e: Exception) {
                FavoriteMoviesDataResult.Failure
            }
        }
    }

    override suspend fun removeFavoriteMovie(id: String): FavoriteMoviesDataResult {
        return withContext(Dispatchers.IO) {
            try {
                appDatabase.favoriteMovieDao().deleteFavoriteMovieById(id)
                FavoriteMoviesDataResult.SuccessDeleteFavoriteMovie
            } catch (e: Exception) {
                FavoriteMoviesDataResult.Failure
            }
        }
    }
}