package com.example.mymoviedb.data.remote

import androidx.paging.PagingSource
import com.example.mymoviedb.data.BaseResult
import com.example.mymoviedb.data.local.MovieEntity
import com.example.mymoviedb.data.response.MovieDetailResponse
import com.example.mymoviedb.data.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {

    fun getPopularMovie() : PagingSource<Int, MovieResponse.ResultsItem>

    fun getDetailMovie(movieId: String) : Flow<BaseResult<MovieDetailResponse>>

//    fun getAllMovieFavorite(): Flow<List<MovieEntity>>
//
//    suspend fun addMovieFavorite(movieEntity: MovieEntity)
//
//    suspend fun deleteMovieFavorite(id: Int)
}