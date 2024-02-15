package com.example.mymoviedb.network.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mymoviedb.core.BaseResult
import com.example.mymoviedb.core.data.toDomain
import com.example.mymoviedb.core.domain.MovieDetail
import com.example.mymoviedb.core.domain.MovieItem
import com.example.mymoviedb.network.domain.repository.MovieRepository
import com.example.mymoviedb.network.remote.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) : MovieRepository {

    override fun getPopularMovie(): PagingSource<Int, MovieItem> {
        return object : PagingSource<Int, MovieItem>() {
            override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
                return state.anchorPosition
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
                return try {
                    val moviePage = params.key ?: 1

                    val response =  apiService.getPopularMovies(moviePage)
                    val movie = response.body()?.toDomain()?.results ?: emptyList()

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

    override suspend fun getDetailMovie(movieId: String): Flow<BaseResult<MovieDetail>> {
        return flow {
            try {
                val response = apiService.getDetailMovies(movieId)
                if (response.isSuccessful) {
                    val body = response.body()!!
                    emit(BaseResult.Success(body.toDomain()))
                }
            } catch (e: Throwable) {
                emit(BaseResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getSearchMovie(query: String): PagingSource<Int, MovieItem> {
        return object : PagingSource<Int, MovieItem>() {
            override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
                return state.anchorPosition
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
                return try {
                    val moviePage = params.key ?: 1

                    val response =  apiService.getSearchMovie(moviePage, query)
                    val movie = response.body()?.toDomain()?.results ?: emptyList()

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

}