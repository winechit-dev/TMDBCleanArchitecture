package com.wcp.tmdbcleanarchitecture.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wcp.domain.interactor.GetPopularMovies
import com.wcp.domain.results.PopularResult
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMovies
) : ViewModel() {


    val fetchPopularMovies: LiveData<PopularResult> = liveData {
        val result: PopularResult = getPopularMovies.invoke()
        emit(result)
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}