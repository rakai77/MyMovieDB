package com.example.mymoviedb.network.domain.interactor

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mymoviedb.core.BaseResult
import com.example.mymoviedb.core.data.remote.dto.MovieDetailResponse
import com.example.mymoviedb.core.domain.MovieDetail
import com.example.mymoviedb.core.domain.MovieItem
import com.example.mymoviedb.network.domain.repository.MovieRepository
import com.example.mymoviedb.network.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {

    override fun getPopularMovie(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = { movieRepository.getPopularMovie() }
        ).flow
    }

    override suspend fun getDetailMovie(movieId: String): Flow<BaseResult<MovieDetail>> {
        return movieRepository.getDetailMovie(movieId)
    }

    override fun getSearchMovie(query: String): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = { movieRepository.getSearchMovie(query) }
        ).flow
    }
}