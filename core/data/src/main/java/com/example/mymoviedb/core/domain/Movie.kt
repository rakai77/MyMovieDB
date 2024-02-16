package com.example.mymoviedb.core.domain

data class Movie(
    val page: Int,
    val totalPages: Int,
    val results: List<MovieItem>,
    val totalResults: Int
)

data class MovieItem(
    val title: String,
    val posterPath: String,
    val voteAverage: Any,
    val id: Int,
)