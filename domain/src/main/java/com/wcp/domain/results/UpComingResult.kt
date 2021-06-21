package com.wcp.domain.results

import com.wcp.domain.models.MoviesResponseModel
import com.wcp.domain.models.UpComingDataModel

sealed class UpComingResult {
    data class Success(val data : MoviesResponseModel<UpComingDataModel>) : UpComingResult()
    object NetworkConnectionFailure : UpComingResult()
    object FeatureFailure : UpComingResult()
    data class ServerError(val error: String) : UpComingResult()
}