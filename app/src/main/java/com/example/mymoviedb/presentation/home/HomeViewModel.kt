package com.example.mymoviedb.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mymoviedb.core.domain.MovieItem
import com.example.mymoviedb.network.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    fun loadPopularMovie() : Flow<PagingData<MovieItem>> {
        return movieUseCase.getPopularMovie().cachedIn(viewModelScope)
    }

}