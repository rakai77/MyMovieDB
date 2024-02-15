package com.example.mymoviedb.database.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymoviedb.core.data.local.MovieEntity
import com.example.mymoviedb.core.domain.Movies
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie")
    suspend fun getAllMovie(): List<MovieEntity>

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun removeMovieFromFavorite(id: Int)

    @Query("UPDATE movie SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteTourism(isFavorite: Boolean, id: Int)
}