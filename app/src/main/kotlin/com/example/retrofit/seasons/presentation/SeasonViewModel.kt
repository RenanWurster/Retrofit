package com.example.retrofit.seasons.presentation

import androidx.lifecycle.*
import com.example.retrofit.episodedetail.data.EpisodesRepository
import com.example.retrofit.episodedetail.domain.Episodes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonViewModel @Inject constructor(private val episodesRepository: EpisodesRepository): ViewModel() {

    class Factory (private val episodesRepository: EpisodesRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SeasonViewModel(episodesRepository) as T
        }

    }

    private val _episodes = MutableLiveData<List<Episodes>>()
    val episodes : LiveData<List<Episodes>> = _episodes


    fun getEpisodesById(id: Int){
        viewModelScope.launch {
            _episodes.value = episodesRepository.getEpisodes(id)
        }

    }

}