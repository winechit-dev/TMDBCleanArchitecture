package com.wcp.tmdbcleanarchitecture.ui.movieDetails

import androidx.lifecycle.*
import com.wcp.domain.interactor.AddFavoriteMovie
import com.wcp.domain.interactor.GetMoviesDetails
import com.wcp.domain.models.FavoriteMovieDataModel
import com.wcp.domain.models.detail.MovieDetailDataModel
import com.wcp.domain.results.FavoriteMoviesDataResult
import com.wcp.domain.results.MovieDetailResult
import com.wcp.tmdbcleanarchitecture.models.UpComingUIModel
import com.wcp.tmdbcleanarchitecture.results.FavoriteMoviesUIResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val getMoviesDetails: GetMoviesDetails,
    private val addFavoriteMovie: AddFavoriteMovie,
) : ViewModel() {

    private val _favoriteMoviesUIState = MutableLiveData<FavoriteMoviesUIResult>()
    val favoriteMoviesUIState: LiveData<FavoriteMoviesUIResult> = _favoriteMoviesUIState

    fun getMovieDetails(movieId: Int): LiveData<MovieDetailResult> = liveData {
        val result: MovieDetailResult = getMoviesDetails.invoke(movieId)
        emit(result)
    }

    fun addFavoriteMovie(movieDetailDataModel: MovieDetailDataModel) {
        viewModelScope.launch {
            val state = addFavoriteMovie.invoke(
                FavoriteMovieDataModel(
                    id = movieDetailDataModel.id.toInt(),
                    poster_path = movieDetailDataModel.poster_path,
                    release_date = movieDetailDataModel.release_date,
                    title = movieDetailDataModel.title,
                    vote_average = movieDetailDataModel.vote_average,
                    vote_count = movieDetailDataModel.vote_count.toInt()

                )
            )
            when (state) {
                is FavoriteMoviesDataResult.SuccessAddFavoriteMovie -> {
                    _favoriteMoviesUIState.value = FavoriteMoviesUIResult.SuccessAddFavoriteMovie
                }
                else -> {
                    _favoriteMoviesUIState.value = FavoriteMoviesUIResult.Failure
                }

            }
        }
    }
}