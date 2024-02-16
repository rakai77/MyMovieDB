package com.example.mymoviedb.database.di

import com.example.mymoviedb.database.domain.interactor.FavoriteInteractor
import com.example.mymoviedb.database.domain.usecase.FavoriteUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteMovieUseCaseModule {

    @Binds
    abstract fun bindFavoriteMovieUseCase(favoriteInteractor: FavoriteInteractor) : FavoriteUseCase

}