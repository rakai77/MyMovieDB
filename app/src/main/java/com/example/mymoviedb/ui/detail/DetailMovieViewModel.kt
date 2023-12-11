package com.example.mymoviedb.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymoviedb.data.BaseResult
import com.example.mymoviedb.data.local.MovieEntity
import com.example.mymoviedb.data.response.MovieDetailResponse
import com.example.mymoviedb.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _state = MutableStateFlow<BaseResult<MovieDetailResponse>>(BaseResult.Loading)
    val state: StateFlow<BaseResult<MovieDetailResponse>> = _state.asStateFlow()

    fun getMovieCast(movieId: String) {
        viewModelScope.launch {
            movieRepository.getDetailMovie(movieId).collect { result ->
                when (result) {
                    is BaseResult.Loading -> {
                        _state.value = BaseResult.Loading
                    }
                    is BaseResult.Error -> {
                        _state.value = BaseResult.Error(result.exception)
                    }
                    is BaseResult.Success -> {
                        _state.value = BaseResult.Success(result.data)
                    }
                }
            }
        }
    }

    fun addFavorite(movie: MovieEntity) {
        viewModelScope.launch {
            movieRepository.addMovieFavorite(movie)
        }
    }

}