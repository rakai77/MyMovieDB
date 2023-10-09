package com.example.mymoviedb.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.mymoviedb.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel()  {

    private var searchJob: Job? = null

    private val currentQuery = MutableStateFlow("")
    val movieList = currentQuery.flatMapLatest { query ->
        movieRepository.getSearchMovie(query).cachedIn(viewModelScope)
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