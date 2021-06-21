package com.wcp.domain.results

import com.wcp.domain.models.MoviesResponseModel
import com.wcp.domain.models.detail.MovieDetailDataModel

sealed class MovieDetailResult {
    data class Success(val data : MoviesResponseModel<MovieDetailDataModel>) : MovieDetailResult()
    object NetworkConnectionFailure : MovieDetailResult()
    object FeatureFailure : MovieDetailResult()
    data class ServerError(val error: String) : MovieDetailResult()
}