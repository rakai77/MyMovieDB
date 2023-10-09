package com.example.mymoviedb.di

import com.example.mymoviedb.domain.repository.MovieRepository
import com.example.mymoviedb.domain.repository.MovieRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieRepository(movieRepositoryImp: MovieRepositoryImp) : MovieRepository
}