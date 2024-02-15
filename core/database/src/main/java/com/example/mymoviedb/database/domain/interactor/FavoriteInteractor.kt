package com.example.mymoviedb.database.domain.interactor

import com.example.mymoviedb.core.BaseResult
import com.example.mymoviedb.core.domain.Movies
import com.example.mymoviedb.database.domain.repository.LocalMovieRepository
import com.example.mymoviedb.database.domain.usecase.FavoriteUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteInteractor @Inject constructor(
    private val localMovieRepository: LocalMovieRepository
) : FavoriteUseCase {
    override suspend fun addMovieFavorite(movies: Movies): Flow<BaseResult<Movies>> {
        return localMovieRepository.addMovieFavorite(movies)
    }

    override suspend fun getAllMovieFavorite(): Flow<BaseResult<List<Movies>>> {
        return localMovieRepository.getAllMovieFavorite()
    }

    override suspend fun deleteMovieFavorite(id: Int): Flow<BaseResult<Movies>> {
        return localMovieRepository.deleteMovieFavorite(id)
    }
}