package com.example.mymoviedb.core.domain

data class Movies(
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
    var isFavorite: Boolean = false
)
