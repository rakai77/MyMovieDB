package com.example.mymoviedb.network.di

import com.example.mymoviedb.network.domain.repository.MovieRepository
import com.example.mymoviedb.network.remote.MovieRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieRepositoryModule {

    @Binds
    abstract fun bindMovieRepository(movieRemoteDataSource: MovieRemoteDataSource) : MovieRepository
}