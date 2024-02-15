package com.example.mymoviedb.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mymoviedb.core.domain.MovieItem
import com.example.mymoviedb.network.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel()  {

    private var searchJob: Job? = null

    private val currentQuery = MutableStateFlow("")
    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadMovieBySearch() : Flow<PagingData<MovieItem>> {
        return currentQuery.flatMapLatest { query ->
            movieUseCase.getSearchMovie(query).cachedIn(viewModelScope)
        }
    }

    fun onSearch(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (query.isEmpty()) {
                currentQuery.value = query
            } else {
                delay(2000)
                currentQuery.value = query
            }
        }
    }

}