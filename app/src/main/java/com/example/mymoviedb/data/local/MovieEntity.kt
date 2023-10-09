package com.example.mymoviedb.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "poster_path")
    var posterPath: String,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double,

    @ColumnInfo(name = "is_favourite")
    var isFavourite: Boolean = false,
)