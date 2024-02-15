package com.example.mymoviedb.database.domain.repository

import com.example.mymoviedb.core.BaseResult
import com.example.mymoviedb.core.domain.Movies
import kotlinx.coroutines.flow.Flow

interface LocalMovieRepository {
    suspend fun addMovieFavorite(movies: Movies) : Flow<BaseResult<Movies>>
    suspend fun getAllMovieFavorite() : Flow<BaseResult<List<Movies>>>
    suspend fun deleteMovieFavorite(id: Int) : Flow<BaseResult<Movies>>
}