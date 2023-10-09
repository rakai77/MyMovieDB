package com.example.mymoviedb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database([MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDb() : MovieDao
}