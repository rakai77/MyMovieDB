package com.example.mymoviedb.core.domain

data class MovieDetail(
    val title: String,
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val overview: String,
    val voteAverage: Double,
)