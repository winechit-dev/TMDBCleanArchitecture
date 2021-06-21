package com.wcp.tmdbcleanarchitecture.ui.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wcp.domain.interactor.GetMoviesDetails
import com.wcp.domain.results.MovieDetailResult
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val getMoviesDetails: GetMoviesDetails
) : ViewModel() {

    fun getMovieDetails(movieId: String): LiveData<MovieDetailResult> = liveData {
        val result: MovieDetailResult = getMoviesDetails.invoke(movieId)
        emit(result)
    }
}