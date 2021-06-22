package com.wcp.tmdbcleanarchitecture.ui.upcoming

import androidx.lifecycle.*
import com.wcp.domain.interactor.AddFavoriteMovie
import com.wcp.domain.interactor.GetUpComingMovies
import com.wcp.domain.interactor.RemoveFavoriteMovie
import com.wcp.domain.models.FavoriteMovieDataModel
import com.wcp.domain.results.FavoriteMoviesDataResult
import com.wcp.domain.results.UpComingResult
import com.wcp.tmdbcleanarchitecture.mapper.UpcomingMoviesUIMapper
import com.wcp.tmdbcleanarchitecture.models.PopularUIModel
import com.wcp.tmdbcleanarchitecture.models.UpComingUIModel
import com.wcp.tmdbcleanarchitecture.results.FavoriteMoviesUIResult
import com.wcp.tmdbcleanarchitecture.results.UpComingUIResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpcomingViewModel @Inject constructor(
    private val getUpComingMovies: GetUpComingMovies,
    private val addFavoriteMovie: AddFavoriteMovie,
    private val removeFavoriteMovie: RemoveFavoriteMovie
) : ViewModel() {

    private val mapper = UpcomingMoviesUIMapper()
    private val _fetchUpComingMovies = MutableLiveData<UpComingUIResult>()
    val fetchUpComingMovies: LiveData<UpComingUIResult> = _fetchUpComingMovies

    private val _favoriteMoviesUIState = MutableLiveData<FavoriteMoviesUIResult>()
    val favoriteMoviesUIState: LiveData<FavoriteMoviesUIResult> = _favoriteMoviesUIState

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            getUpComingMovies.invoke().let { result ->
                when (result) {
                    is UpComingResult.Success -> {
                        _fetchUpComingMovies.value =
                            UpComingUIResult.Success(mapper.transform(result.data.results))
                    }
                    is UpComingResult.FeatureFailure -> {
                        _fetchUpComingMovies.value = UpComingUIResult.FeatureFailure
                    }
                    is UpComingResult.NetworkConnectionFailure -> {
                        _fetchUpComingMovies.value = UpComingUIResult.NetworkConnectionFailure
                    }
                    is UpComingResult.ServerError -> {
                        _fetchUpComingMovies.value = UpComingUIResult.ServerError(result.error)
                    }
                    else -> _fetchUpComingMovies.value = UpComingUIResult.FeatureFailure
                }
            }

        }

    }

    fun addFavoriteMovie(upComingUIModel: UpComingUIModel) {
        viewModelScope.launch {
            val state = addFavoriteMovie.invoke(
                FavoriteMovieDataModel(
                    id = upComingUIModel.id,
                    poster_path = upComingUIModel.poster_path,
                    release_date = upComingUIModel.release_date,
                    title = upComingUIModel.title,
                    vote_average = upComingUIModel.vote_average,
                    vote_count = upComingUIModel.vote_count

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

    fun removeFavoriteMovie(id: String) {
        viewModelScope.launch {
            when (removeFavoriteMovie.invoke(id)) {
                is FavoriteMoviesDataResult.SuccessDeleteFavoriteMovie -> {
                    _favoriteMoviesUIState.value = FavoriteMoviesUIResult.SuccessDeleteFavoriteMovie
                }
                else -> {
                    _favoriteMoviesUIState.value = FavoriteMoviesUIResult.Failure
                }
            }
        }
    }

}