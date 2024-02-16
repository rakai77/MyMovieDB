package com.example.mymoviedb.presentation.detail

import com.example.mymoviedb.core.domain.MovieDetail
import com.example.mymoviedb.core.domain.Movies

sealed class DetailMovieUiState {
    data class Success(val movieDetail: MovieDetail) : DetailMovieUiState()
    data class SuccessFavorite(val movie: Movies) : DetailMovieUiState()
    data class Error(val error: String) : DetailMovieUiState()
    data class Loading(val loading: Boolean) : DetailMovieUiState()
    object Idle : DetailMovieUiState()
}