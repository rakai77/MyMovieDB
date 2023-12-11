package com.example.mymoviedb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie")
    fun getAllMovie() : Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getMovieFavorite() : Flow<List<MovieEntity>>

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun removeMovieFromFavorite(id: Int?)

    @Query("UPDATE movie SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteTourism(isFavorite: Boolean, id: Int?)
}