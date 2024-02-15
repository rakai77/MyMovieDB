package com.example.mymoviedb.core.data.remote.dto.mapper

import com.example.mymoviedb.core.data.remote.dto.MovieDetailResponse
import com.example.mymoviedb.core.data.remote.dto.MovieItemResult
import com.example.mymoviedb.core.data.remote.dto.MovieResponse
import com.example.mymoviedb.core.domain.Movie
import com.example.mymoviedb.core.domain.MovieDetail
import com.example.mymoviedb.core.domain.MovieItem

fun MovieResponse.toDomain() = Movie(
    page = this.page ?: 0,
    totalPages = this.totalPages ?: 0,
    totalResults = this.totalResults ?: 0,
    results = this.results?.map { it.toDomain() } ?: emptyList()
)

fun MovieItemResult.toDomain() = MovieItem(
    title = this.title ?: "",
    posterPath = this.posterPath ?: "",
    voteAverage = this.voteAverage ?: "",
    id = this.id ?: 0
)

fun MovieDetailResponse.toDomain() = MovieDetail(
    title = this.title ?: "",
    id = this.id ?: 0,
    posterPath = this.posterPath ?: "",
    releaseDate = this.releaseDate ?: "",
    overview = this.overview ?: "",
    voteAverage = this.voteAverage ?: 0.0
)