package com.example.mymoviedb.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mymoviedb.data.BaseResult
import com.example.mymoviedb.data.local.MovieEntity
import com.example.mymoviedb.data.remote.MovieRemoteDataSource
import com.example.mymoviedb.data.response.MovieDetailResponse
import com.example.mymoviedb.data.response.MovieResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getPopularMovie(): Flow<PagingData<MovieResponse.ResultsItem>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = { movieRemoteDataSource.getPopularMovie() }
        ).flow
    }

    override fun getDetailMovie(movieId: String): Flow<BaseResult<MovieDetailResponse>> = movieRemoteDataSource.getDetailMovie(movieId)
    override fun getSearchMovie(query: String): Flow<PagingData<MovieResponse.ResultsItem>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = { movieRemoteDataSource.getSearchMovie(query) }
        ).flow
    }

    override fun getAllMovieFavorite(): Flow<List<MovieEntity>> {
        return movieRemoteDataSource.getAllMovieFavorite()
    }

    override suspend fun addMovieFavorite(movieEntity: MovieEntity) {
        return movieRemoteDataSource.addMovieFavorite(movieEntity)
    }

    override suspend fun updateMovieFavorite(isChecked: Boolean, id: Int?) {
        return movieRemoteDataSource.updateMovieFavorite(isChecked, id)
    }

    override suspend fun deleteMovieFavorite(id: Int) {
        return movieRemoteDataSource.deleteMovieFavorite(id)
    }

//    override fun getAllMovieFavorite(): Flow<List<MovieEntity>> = movieRemoteDataSource.getAllMovieFavorite()
//
//    override suspend fun addMovieFavorite(movieEntity: MovieEntity) = movieRemoteDataSource.addMovieFavorite(movieEntity)
//
//    override suspend fun deleteMovieFavorite(id: Int) = movieRemoteDataSource.deleteMovieFavorite(id)
}