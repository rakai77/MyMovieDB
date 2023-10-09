package com.example.mymoviedb.di

import android.content.Context
import androidx.room.Room
import com.example.mymoviedb.data.local.MovieDao
import com.example.mymoviedb.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        "movie_db"
    ).allowMainThreadQueries().build()

    @Provides
    fun provideMovieDao(db: MovieDatabase): MovieDao = db.movieDb()
}