package com.example.mymoviedb.database.di

import com.example.mymoviedb.database.domain.repository.LocalMovieRepository
import com.example.mymoviedb.database.local.MovieLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalMovieRepositoryModule {

    @Binds
    abstract fun bindLocalMovieRepository(movieLocalDataSource: MovieLocalDataSource) : LocalMovieRepository

}