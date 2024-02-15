package com.example.mymoviedb.presentation.detail

import com.example.mymoviedb.core.domain.MovieDetail

sealed class DetailMovieUiState {
    data class Success(val movieDetail: MovieDetail) : DetailMovieUiState()
    data class Error(val error: Throwable) : DetailMovieUiState()
    data class Loading(val loading: Boolean) : DetailMovieUiState()
    object Idle : DetailMovieUiState()
}