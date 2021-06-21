package com.wcp.domain.results

import com.wcp.domain.models.MoviesResponseModel
import com.wcp.domain.models.PopularDataModel

sealed class PopularResult {
    data class Success(val data : MoviesResponseModel<PopularDataModel>) : PopularResult()
    object NetworkConnectionFailure : PopularResult()
    object FeatureFailure : PopularResult()
    data class ServerError(val error: String) : PopularResult()
}