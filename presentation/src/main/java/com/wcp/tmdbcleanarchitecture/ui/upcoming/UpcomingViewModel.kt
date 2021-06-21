package com.wcp.tmdbcleanarchitecture.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wcp.domain.interactor.GetUpComingMovies
import com.wcp.domain.results.UpComingResult
import javax.inject.Inject

class UpcomingViewModel @Inject constructor(
    private val getUpComingMovies: GetUpComingMovies
) : ViewModel() {

    val fetchUpComingMovies: LiveData<UpComingResult> = liveData {
        val result: UpComingResult = getUpComingMovies.invoke()
        emit(result)

    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}