package com.example.mymoviedb.core.data

import com.example.mymoviedb.core.data.remote.dto.MovieItemResult
import com.example.mymoviedb.core.data.remote.dto.MovieResponse

object DummyData {

    val movieResponse = MovieResponse(
        page = 1,
        totalPages = 5,
        totalResults = 10,
        results = listOf(
            MovieItemResult(
                title = "Movie 1",
                posterPath = "/poster1.jpg",
                voteAverage = "8.5",
                id = 1
            ),
            MovieItemResult(
                title = "Movie 2",
                posterPath = "/poster2.jpg",
                voteAverage = "7.9",
                id = 2
            )
        )
    )
}