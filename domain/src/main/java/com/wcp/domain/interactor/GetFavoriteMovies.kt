package com.wcp.domain.interactor

import com.wcp.domain.repository.DataRepository
import javax.inject.Inject

class GetFavoriteMovies @Inject constructor(private val repository : DataRepository) {
    suspend operator fun invoke() = repository.loadFavoriteMovies()
}