package com.example.retrofit.shows.presentation

import android.util.Log
import androidx.lifecycle.*
import com.example.retrofit.shows.domain.Series
import com.example.retrofit.shows.data.SeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(private val seriesRepository: SeriesRepository): ViewModel() {

    private val _series = MutableLiveData<List<Series>>()
    val series : LiveData<List<Series>> = _series

    init {
        viewModelScope.launch {
            _series.value = seriesRepository.getSeries()
        }
    }

    fun onSearch(name: String) {
        viewModelScope.launch {
            if (name.isNotEmpty()) {
                val searchResults = seriesRepository.searchShows(name)
                Log.d("SeriesViewModel", "Search results: $searchResults")
                _series.value = searchResults.map { it.show }
            } else {
                val allSeries = seriesRepository.getSeries()
                _series.value = allSeries
            }
        }
    }
}
