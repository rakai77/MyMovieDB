package com.example.mymoviedb.network.domain.repository

import androidx.paging.PagingSource
import com.example.mymoviedb.core.BaseResult
import com.example.mymoviedb.core.data.remote.dto.MovieDetailResponse
import com.example.mymoviedb.core.domain.MovieDetail
import com.example.mymoviedb.core.domain.MovieItem
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovie() : PagingSource<Int, MovieItem>

    suspend fun getDetailMovie(movieId: String) : Flow<BaseResult<MovieDetail>>

    fun getSearchMovie(query: String) : PagingSource<Int, MovieItem>
}