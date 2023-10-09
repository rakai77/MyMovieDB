package com.example.mymoviedb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface MovieDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMovie(movie: MovieEntity)
}