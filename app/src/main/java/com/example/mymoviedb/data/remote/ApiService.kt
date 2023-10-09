package com.example.mymoviedb.data.remote

import com.example.mymoviedb.BuildConfig
import com.example.mymoviedb.data.response.MovieDetailResponse
import com.example.mymoviedb.data.response.MovieResponse
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
