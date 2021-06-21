package com.wcp.domain.models

data class MoviesResponseModel<N>(
    val dates: Dates,
    val page: Int,
    val results: List<N>,
    val total_pages: Int,
    val total_results: Int
)