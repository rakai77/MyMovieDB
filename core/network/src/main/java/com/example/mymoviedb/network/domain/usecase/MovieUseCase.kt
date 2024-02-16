package com.example.mymoviedb.network.domain.usecase

import androidx.paging.PagingData
import com.example.mymoviedb.core.BaseResult
import com.example.mymoviedb.core.data.remote.dto.MovieDetailResponse
import com.example.mymoviedb.core.data.remote.dto.MovieItemResult
import com.example.mymoviedb.core.data.remote.dto.MovieResponse
import com.example.mymoviedb.core.domain.MovieDetail
import com.example.mymoviedb.core.domain.MovieItem
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getPopularMovie() : Flow<PagingData<MovieItem>>
    suspend fun getDetailMovie(movieId: String) : Flow<BaseResult<MovieDetail>>
    fun getSearchMovie(query: String) : Flow<PagingData<MovieItem>>
}