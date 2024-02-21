package com.example.mymoviedb.core

import com.example.mymoviedb.core.data.DummyData
import com.example.mymoviedb.core.data.remote.dto.MovieItemResult
import com.example.mymoviedb.core.data.remote.dto.MovieResponse
import com.example.mymoviedb.core.data.toDomain
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class DataMapperTest {

    private lateinit var dummy: DummyData

    @Before
    fun setUp() {
        dummy = DummyData
    }


    @Test
    fun `test mapping MovieResponse toDomain`() {

        val movieResponse = mockk<MovieResponse> {
            every { page } returns dummy.movieResponse.page
            every { totalPages } returns dummy.movieResponse.totalPages
            every { totalResults } returns dummy.movieResponse.totalResults
            every { results } returns dummy.movieResponse.results
        }

        val movie = movieResponse.toDomain()

        assertEquals(dummy.movieResponse.page, movie.page)
        assertEquals(dummy.movieResponse.totalPages, movie.totalPages)
        assertEquals(dummy.movieResponse.totalResults, movie.totalResults)
        assertEquals(dummy.movieResponse.results?.size, movie.results.size)
    }

    @Test
    fun `test mapping MovieItemResult toDomain`() {

        val movieItemResult = mockk<MovieItemResult> {
            every { title } returns "Movie 1"
            every { posterPath } returns "/post.png"
            every { voteAverage } returns "8.0"
            every { id } returns 1
        }

        val movie = movieItemResult.toDomain()

        assertEquals("Movie 1", movie.title)
        assertEquals("/post.png", movie.posterPath)
        assertEquals("8.0", movie.voteAverage)
        assertEquals(1, movie.id)
    }
}