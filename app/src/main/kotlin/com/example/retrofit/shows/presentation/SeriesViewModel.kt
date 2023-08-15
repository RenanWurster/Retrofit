package com.example.retrofit.shows.presentation

import androidx.lifecycle.*
import com.example.retrofit.domain.Series
import com.example.retrofit.shows.data.SeriesRepository
import kotlinx.coroutines.launch

class SeriesViewModel(private val seriesRepository: SeriesRepository): ViewModel() {

    class Factory(private val seriesRepository: SeriesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
           return SeriesViewModel(seriesRepository) as T
        }
    }

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
                _series.value = searchResults
            } else {
                // Se a pesquisa estiver vazia, recarregue todas as séries
                val allSeries = seriesRepository.getSeries()
                _series.value = allSeries
            }
        }
    }


}
