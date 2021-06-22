package com.wcp.tmdbcleanarchitecture.results

import com.wcp.domain.models.FavoriteMovieDataModel
import com.wcp.domain.results.FavoriteMoviesDataResult

sealed class FavoriteMoviesUIResult {
    data class Success(val data : List<FavoriteMovieDataModel>) : FavoriteMoviesUIResult()
    object SuccessAddFavoriteMovie : FavoriteMoviesUIResult()
    object SuccessDeleteFavoriteMovie : FavoriteMoviesUIResult()
    object Failure : FavoriteMoviesUIResult()

}