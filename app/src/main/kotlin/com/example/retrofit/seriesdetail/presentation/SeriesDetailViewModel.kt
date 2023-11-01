package com.example.retrofit.seriesdetail.presentation

import androidx.lifecycle.*
import com.example.retrofit.episodedetail.data.EpisodesRepository
import com.example.retrofit.seasons.presentation.SeasonViewModel
import com.example.retrofit.seriesdetail.data.SeriesDetailRepository
import com.example.retrofit.seriesdetail.domain.Seasons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesDetailViewModel @Inject constructor(private val detailRepository: SeriesDetailRepository): ViewModel() {

    class Factory(private val repository: SeriesDetailRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SeriesDetailViewModel(repository) as T
        }

    }

    private val _seasons = MutableLiveData<List<Seasons>>()
    val seasons : LiveData<List<Seasons>> = _seasons


    fun getSeasonsById(id: Int){
        viewModelScope.launch {
            _seasons.value = detailRepository.getSeasons(id)
        }

    }

}