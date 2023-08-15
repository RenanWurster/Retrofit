package com.example.retrofit.shows.data

import com.example.retrofit.data.network.ApiService
import com.example.retrofit.domain.Series

class SeriesRepository(private val apiService: ApiService) {
    suspend fun getSeries(): List<Series> {
        return apiService.getSeries()
    }

    suspend fun searchShows(name: String): List<Series> {
        val searchResults = apiService.searchShows(name)
        return searchResults.mapNotNull { apiSeries ->
            if (apiSeries?.name != null && apiSeries?.image != null) {
                Series(
                    id = apiSeries.id ?: 0,
                    name = apiSeries.name,
                    image = apiSeries.image,
                    genres = apiSeries.genres,
                    summary = apiSeries.summary,
                    type = apiSeries.type
                )
            } else {
                null
            }
        }
    }

}

