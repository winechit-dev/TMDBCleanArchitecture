package com.wcp.domain.interactor

import com.wcp.domain.repository.DataRepository
import javax.inject.Inject

class RemoveFavoriteMovie @Inject constructor(private val repository : DataRepository) {
    suspend operator fun invoke(id : String) = repository.removeFavoriteMovie(id)
}