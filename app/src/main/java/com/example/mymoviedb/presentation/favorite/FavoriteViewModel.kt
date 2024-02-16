package com.example.mymoviedb.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymoviedb.core.BaseResult
import com.example.mymoviedb.database.domain.usecase.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteMovie: FavoriteUseCase
) : ViewModel() {

    private val _favMovie = MutableStateFlow<FavoriteMovieUiState>(FavoriteMovieUiState.Idle)
    val favMovie get() = _favMovie.asStateFlow()

    fun getMovieFavorite() {
        viewModelScope.launch {
            favoriteMovie.getAllMovieFavorite()
                .onStart {
                    _favMovie.emit(FavoriteMovieUiState.Loading(true))
                }
                .catch {
                    _favMovie.emit(FavoriteMovieUiState.Loading(false))
                }
                .collect { result ->
                    _favMovie.emit(FavoriteMovieUiState.Loading(false))
                    when(result) {
                        is BaseResult.Success -> {
                            _favMovie.emit(FavoriteMovieUiState.Success(result.data))
                        }
                        is BaseResult.Error -> {
                            _favMovie.emit(FavoriteMovieUiState.Error(result.message))
                        }
                        else -> Unit
                    }
                }
        }
    }

    fun removeMovieFavorite(id: Int) {
        viewModelScope.launch {
            favoriteMovie.deleteMovieFavorite(id)
                .collect { result ->
                    when(result) {
                        is BaseResult.Success -> {
                            _favMovie.emit(FavoriteMovieUiState.SuccessDelete(result.data))
                        }
                        is BaseResult.Error -> {
                            _favMovie.emit(FavoriteMovieUiState.Error(result.message))
                        }
                        else -> Unit
                    }
                }
        }
    }

}