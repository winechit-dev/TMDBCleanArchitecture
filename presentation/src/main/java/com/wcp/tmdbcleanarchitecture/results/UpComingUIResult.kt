package com.wcp.tmdbcleanarchitecture.results

import com.wcp.domain.models.MoviesResponseModel
import com.wcp.domain.models.UpComingDataModel
import com.wcp.tmdbcleanarchitecture.models.UpComingUIModel

sealed class UpComingUIResult {
    data class Success(val data : List<UpComingUIModel>) : UpComingUIResult()
    object NetworkConnectionFailure : UpComingUIResult()
    object FeatureFailure : UpComingUIResult()
    data class ServerError(val error: String) : UpComingUIResult()
}