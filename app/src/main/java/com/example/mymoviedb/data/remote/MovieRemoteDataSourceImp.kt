package com.example.mymoviedb.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mymoviedb.data.BaseResult
import com.example.mymoviedb.data.response.MovieDetailResponse
import com.example.mymoviedb.data.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
) : MovieRemoteDataSource {

    override fun getPopularMovie(): PagingSource<Int, MovieResponse.ResultsItem> {
        return object : PagingSource<Int, MovieResponse.ResultsItem>() {
            override fun getRefreshKey(state: PagingState<Int, MovieResponse.ResultsItem>): Int? {
                return state.anchorPosition
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse.ResultsItem> {
                return try {
                    val moviePage = params.key ?: 1

                    val response =  apiService.getPopularMovies(moviePage)
                    val movie = response.body()?.results ?: emptyList()

                    LoadResult.Page(
                        data = movie,
                        prevKey = null,
                        nextKey = if (movie.isEmpty()) null else moviePage + 1
                    )
                } catch (e: IOException) {
                    LoadResult.Error(e)
                } catch (e: HttpException) {
                    LoadResult.Error(e)
                }
            }
        }
    }

    override fun getDetailMovie(movieId: String): Flow<BaseResult<MovieDetailResponse>> {
        return flow {
            try {
                val response = apiService.getDetailMovies(movieId)
                if (response.isSuccessful) {
                    val body = response.body()!!
                    emit(BaseResult.Success(body))
                }
            } catch (e: Throwable) {
                emit(BaseResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

//    override fun getAllMovieFavorite(): Flow<List<MovieEntity>> {
//        return movieDao.loadAllMovie()
//    }
//
//    override suspend fun addMovieFavorite(movieEntity: MovieEntity) {
//        return movieDao.insertMovie(movieEntity)
//    }
//
//    override suspend fun deleteMovieFavorite(id: Int) {
//        return movieDao.deleteMovie(id)
//    }
}