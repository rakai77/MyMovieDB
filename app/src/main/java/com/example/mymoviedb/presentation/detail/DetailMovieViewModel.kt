package com.example.mymoviedb.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymoviedb.core.BaseResult
import com.example.mymoviedb.core.domain.Movie
import com.example.mymoviedb.core.domain.Movies
import com.example.mymoviedb.database.domain.usecase.FavoriteUseCase
import com.example.mymoviedb.network.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val movieFavoriteUseCase: FavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<DetailMovieUiState>(DetailMovieUiState.Idle)
    val state: StateFlow<DetailMovieUiState> = _state.asStateFlow()

    fun getMovieCast(movieId: String) {
        viewModelScope.launch {
            movieUseCase.getDetailMovie(movieId)
                .onStart {
                    _state.emit(DetailMovieUiState.Loading(true))
                }
                .catch {
                    _state.emit(DetailMovieUiState.Loading(false))
                    Log.d("catch", "getMovieCast: ${it.message}")
                }
                .collect { result ->
                    _state.emit(DetailMovieUiState.Loading(false))
                    when (result) {
                        is BaseResult.Success -> {
                            _state.emit(DetailMovieUiState.Success(result.data))
                        }
                        is BaseResult.Error -> {
                            _state.emit(DetailMovieUiState.Error(result.message))
                        }
                        else -> Unit
                    }
                }
        }
    }

    fun addMovieFavorite(movies: Movies) {
        viewModelScope.launch {
            _state.emit(DetailMovieUiState.Loading(true))
            movieFavoriteUseCase.addMovieFavorite(movies)
                .collect { result ->
                    _state.emit(DetailMovieUiState.Loading(false))
                    when (result) {
                        is BaseResult.Success -> {
                            _state.emit(DetailMovieUiState.SuccessFavorite(result.data))
                        }
                        is BaseResult.Error -> {
                            _state.emit(DetailMovieUiState.Error(result.message))
                        }
                        else -> Unit
                    }
                }
        }
    }
}