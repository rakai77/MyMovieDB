package com.example.mymoviedb.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.mymoviedb.data.local.MovieEntity
import com.example.mymoviedb.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovieFavorite() : Flow<List<MovieEntity>> {
        return movieRepository.getAllMovieFavorite()
    }
}