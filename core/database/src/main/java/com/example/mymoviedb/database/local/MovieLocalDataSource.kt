package com.example.mymoviedb.database.local

import android.database.sqlite.SQLiteConstraintException
import com.example.mymoviedb.core.BaseResult
import com.example.mymoviedb.core.data.toDomain
import com.example.mymoviedb.core.data.toEntity
import com.example.mymoviedb.core.domain.Movies
import com.example.mymoviedb.database.domain.MovieDao
import com.example.mymoviedb.database.domain.repository.LocalMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.sql.SQLException
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) : LocalMovieRepository{
    override suspend fun addMovieFavorite(movies: Movies): Flow<BaseResult<Movies>> {
        return flow<BaseResult<Movies>> {
            movieDao.insertMovie(movies.toEntity())
            emit(BaseResult.Success(movies))
        }.catch { e ->
            when(e) {
                is SQLException -> {
                    emit(BaseResult.Error(e.message ?: ""))
                }
                is SQLiteConstraintException -> {
                    emit(BaseResult.Error(e.message ?: ""))
                }
                else -> {
                    emit(BaseResult.Error(e.message ?: ""))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllMovieFavorite(): Flow<BaseResult<List<Movies>>> {
        return flow {
            val movie = movieDao.getAllMovie().map { it.toDomain() }
            if (movie.isNotEmpty()) {
                emit(BaseResult.Success(movie))
            } else {
                emit(BaseResult.Error(""))
            }
        }.catch {
            emit(BaseResult.Error(it.message ?: ""))
        }.flowOn(Dispatchers.IO)
    }


    override suspend fun deleteMovieFavorite(id: Int): Flow<BaseResult<Movies>> {
        return flow<BaseResult<Movies>> {
            movieDao.removeMovieFromFavorite(id)
        }.flowOn(Dispatchers.IO)
    }

}