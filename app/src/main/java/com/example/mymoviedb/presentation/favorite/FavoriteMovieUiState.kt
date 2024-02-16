package com.example.mymoviedb.presentation.favorite

import com.example.mymoviedb.core.domain.Movies

sealed class FavoriteMovieUiState {
    data class Success(val movies: List<Movies>) : FavoriteMovieUiState()
    data class SuccessDelete(val movies: Movies) : FavoriteMovieUiState()
    data class Error(val error: String) : FavoriteMovieUiState()
    data class Loading(val loading: Boolean) : FavoriteMovieUiState()
    object Idle : FavoriteMovieUiState()
}