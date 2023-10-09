package com.example.mymoviedb.di

import com.example.mymoviedb.data.remote.MovieRemoteDataSource
import com.example.mymoviedb.data.remote.MovieRemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteRepositoryModule {

    @Binds
    abstract fun provideMovieRemoteDataSource(movieRemoteDataSourceImp: MovieRemoteDataSourceImp) : MovieRemoteDataSource
}