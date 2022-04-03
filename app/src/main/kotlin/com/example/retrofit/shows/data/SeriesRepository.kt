package com.example.retrofit.shows.data

import com.example.retrofit.data.network.ApiService
import com.example.retrofit.domain.Series

class SeriesRepository(private val apiService: ApiService) {
    suspend fun getSeries(): List<Series> {
        return apiService.getSeries()
    }
}

