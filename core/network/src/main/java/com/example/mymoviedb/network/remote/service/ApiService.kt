package com.example.mymoviedb.network.remote.service

import com.example.mymoviedb.core.data.remote.dto.MovieDetailResponse
import com.example.mymoviedb.core.data.remote.dto.MovieResponse
import com.example.mymoviedb.network.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") clientId: String = BuildConfig.API_KEY
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovies(
        @Path("movie_id") movieId: String,
        @Query("api_key") clientId: String = BuildConfig.API_KEY
    ): Response<MovieDetailResponse>

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("api_key") clientId: String = BuildConfig.API_KEY
    ): Response<MovieResponse>
}
