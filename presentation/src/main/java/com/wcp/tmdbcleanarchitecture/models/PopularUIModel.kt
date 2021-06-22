package com.wcp.tmdbcleanarchitecture.models

data class PopularUIModel(
    val id: Int,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)