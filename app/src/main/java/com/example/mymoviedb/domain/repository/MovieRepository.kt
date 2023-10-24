package com.example.mymoviedb.domain.repository

import androidx.paging.PagingData
import com.example.mymoviedb.data.BaseResult
import com.example.mymoviedb.data.local.MovieEntity
import com.example.mymoviedb.data.response.MovieDetailResponse
import com.example.mymoviedb.data.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovie() : Flow<PagingData<MovieResponse.ResultsItem>>

    fun getDetailMovie(movieId: String) : Flow<BaseResult<MovieDetailResponse>>

    fun getSearchMovie(query: String) : Flow<PagingData<MovieResponse.ResultsItem>>

    fun getAllMovieFavorite(): Flow<List<MovieEntity>>

    suspend fun addMovieFavorite(movieEntity: MovieEntity)

    suspend fun updateMovieFavorite(isChecked: Boolean, id: Int?)

    suspend fun deleteMovieFavorite(id: Int)
}