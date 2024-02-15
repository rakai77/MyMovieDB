package com.example.mymoviedb.database.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymoviedb.core.data.local.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDb(): MovieDao
}