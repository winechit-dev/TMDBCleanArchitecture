package com.wcp.domain.interactor

import com.wcp.domain.models.FavoriteMovieDataModel
import com.wcp.domain.repository.DataRepository
import javax.inject.Inject

class AddFavoriteMovie @Inject constructor(private val repository : DataRepository) {
    suspend operator fun invoke(movies : FavoriteMovieDataModel) = repository.addFavoriteMovie(movies)
}