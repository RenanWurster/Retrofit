package com.example.retrofit.episodedetail.data

import com.example.retrofit.data.network.ApiService
import com.example.retrofit.episodedetail.domain.Episodes
import javax.inject.Inject

class EpisodesRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getEpisodes(id : Int): List<Episodes> {
        return apiService.getEpisodes(id)
    }
}