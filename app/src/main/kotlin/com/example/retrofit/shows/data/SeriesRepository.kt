package com.example.retrofit.shows.data

import com.example.retrofit.data.network.ApiService
import com.example.retrofit.shows.domain.SearchSeries
import com.example.retrofit.shows.domain.Series



class SeriesRepository(private val apiService: ApiService) {
    suspend fun getSeries(): List<Series> {
        return apiService.getSeries()
    }

    suspend fun searchShows(name: String): List<SearchSeries> {
        return apiService.searchShows(name)
    }


}