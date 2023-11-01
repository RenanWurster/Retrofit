package com.example.retrofit.seriesdetail.data

import com.example.retrofit.data.network.ApiService
import com.example.retrofit.seriesdetail.domain.Seasons
import javax.inject.Inject

class SeriesDetailRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getSeasons(id : Int): List<Seasons> {
        return apiService.getSeasons(id)
    }
}