package com.example.retrofit.shows.data

import com.example.retrofit.data.network.ApiService
import com.example.retrofit.shows.domain.Series

class SeriesRepository(private val apiService: ApiService) {
    suspend fun getSeries(): List<Series> {
        return apiService.getSeries()
    }

    suspend fun searchShows(name: String): List<Series> {
        val searchResults = apiService.searchShows(name)
        return searchResults.mapNotNull { apiSeries ->
            apiSeries.takeIf { it.name != null && it.image != null }?.let { series ->
                Series(
                    id = series.id ?: throw IllegalArgumentException("Series ID cannot be null"),
                    name = series.name!!,
                    image = series.image!!,
                    genres = series.genres,
                    summary = series.summary,
                    type = series.type
                )
            }
        }
    }


}

