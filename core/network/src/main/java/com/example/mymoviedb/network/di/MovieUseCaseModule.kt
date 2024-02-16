package com.example.mymoviedb.network.di

import com.example.mymoviedb.network.domain.usecase.MovieUseCase
import com.example.mymoviedb.network.domain.interactor.MovieInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieUseCaseModule {

    @Binds
    abstract fun bindMovieUseCase(movieInteractor: MovieInteractor) : MovieUseCase
}