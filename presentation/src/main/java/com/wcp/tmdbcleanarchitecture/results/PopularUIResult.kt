package com.wcp.tmdbcleanarchitecture.results

import com.wcp.tmdbcleanarchitecture.models.PopularUIModel

sealed class PopularUIResult {
    data class Success(val data : List<PopularUIModel>) : PopularUIResult()
    object NetworkConnectionFailure : PopularUIResult()
    object FeatureFailure : PopularUIResult()
    data class ServerError(val error: String) : PopularUIResult()
}