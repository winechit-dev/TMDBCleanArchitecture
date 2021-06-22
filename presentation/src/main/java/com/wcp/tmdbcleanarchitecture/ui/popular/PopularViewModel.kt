package com.wcp.tmdbcleanarchitecture.ui.popular

import androidx.lifecycle.*
import com.wcp.domain.interactor.AddFavoriteMovie
import com.wcp.domain.interactor.GetPopularMovies
import com.wcp.domain.interactor.RemoveFavoriteMovie
import com.wcp.domain.models.FavoriteMovieDataModel
import com.wcp.domain.results.FavoriteMoviesDataResult
import com.wcp.domain.results.PopularResult
import com.wcp.tmdbcleanarchitecture.mapper.PopularMoviesUIMapper
import com.wcp.tmdbcleanarchitecture.models.PopularUIModel
import com.wcp.tmdbcleanarchitecture.results.FavoriteMoviesUIResult
import com.wcp.tmdbcleanarchitecture.results.PopularUIResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMovies,
    private val addFavoriteMovie: AddFavoriteMovie,
    private val removeFavoriteMovie: RemoveFavoriteMovie
) : ViewModel() {

    private val mapper = PopularMoviesUIMapper()
    private val _fetchPopularMovies = MutableLiveData<PopularUIResult>()
    val fetchPopularMovies: LiveData<PopularUIResult> = _fetchPopularMovies

    private val _favoriteMoviesUIState = MutableLiveData<FavoriteMoviesUIResult>()
    val favoriteMoviesUIState: LiveData<FavoriteMoviesUIResult> = _favoriteMoviesUIState

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            getPopularMovies.invoke().let { result ->
                when (result) {
                    is PopularResult.Success -> {
                        _fetchPopularMovies.value =
                            PopularUIResult.Success(mapper.transform(result.data.results))
                    }
                    is PopularResult.FeatureFailure -> {
                        _fetchPopularMovies.value = PopularUIResult.FeatureFailure
                    }
                    is PopularResult.NetworkConnectionFailure -> {
                        _fetchPopularMovies.value = PopularUIResult.NetworkConnectionFailure
                    }
                    is PopularResult.ServerError -> {
                        _fetchPopularMovies.value = PopularUIResult.ServerError(result.error)
                    }
                    else -> _fetchPopularMovies.value = PopularUIResult.FeatureFailure
                }


            }
        }
    }

    fun addFavoriteMovie(popularUIModel: PopularUIModel) {
        viewModelScope.launch {
            val state = addFavoriteMovie.invoke(
                FavoriteMovieDataModel(
                    id = popularUIModel.id,
                    poster_path = popularUIModel.poster_path,
                    release_date = popularUIModel.release_date,
                    title = popularUIModel.title,
                    vote_average = popularUIModel.vote_average,
                    vote_count = popularUIModel.vote_count

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