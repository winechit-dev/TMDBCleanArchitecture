package com.wcp.tmdbcleanarchitecture.ui.favorites

import androidx.lifecycle.*
import com.wcp.domain.interactor.GetFavoriteMovies
import com.wcp.domain.results.FavoriteMoviesDataResult
import com.wcp.tmdbcleanarchitecture.results.FavoriteMoviesUIResult
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val getFavoriteMovies: GetFavoriteMovies
) : ViewModel() {


    val fetchFavoriteMovies: LiveData<FavoriteMoviesUIResult> = liveData {
        when (val result = getFavoriteMovies.invoke()) {
            is FavoriteMoviesDataResult.Success -> {
                emit(FavoriteMoviesUIResult.Success(result.data))
            }
            is FavoriteMoviesDataResult.Failure -> {
                emit(FavoriteMoviesUIResult.Failure)
            }
        }

    }

}