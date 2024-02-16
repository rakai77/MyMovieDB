package com.example.mymoviedb.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null,

    @field:SerializedName("results")
    val results: List<MovieItemResult>? = null
)

data class MovieItemResult(

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Any? = null,

    @field:SerializedName("id")
    val id: Int? = null,
)