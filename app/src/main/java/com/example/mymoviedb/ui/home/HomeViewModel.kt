package com.example.mymoviedb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.mymoviedb.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    val movieList = movieRepository.getPopularMovie().cachedIn(viewModelScope)

}