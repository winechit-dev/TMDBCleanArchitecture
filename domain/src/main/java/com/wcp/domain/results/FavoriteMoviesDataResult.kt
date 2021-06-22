package com.wcp.domain.results

import com.wcp.domain.models.FavoriteMovieDataModel

sealed class FavoriteMoviesDataResult {
    data class Success(val data : List<FavoriteMovieDataModel>) : FavoriteMoviesDataResult()
    object SuccessAddFavoriteMovie : FavoriteMoviesDataResult()
    object SuccessDeleteFavoriteMovie : FavoriteMoviesDataResult()
    object Failure : FavoriteMoviesDataResult()


}