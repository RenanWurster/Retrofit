package com.example.retrofit.data.network


import com.example.retrofit.seriesdetail.domain.Seasons
import com.example.retrofit.shows.domain.Series
import com.example.retrofit.episodedetail.domain.Episodes
import com.example.retrofit.shows.domain.SearchSeries
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/shows")
    suspend fun getSeries(): List<Series>

    @GET("/shows/{id}/seasons")
    suspend fun getSeasons(@Path("id") id : Int): List<Seasons>

    @GET("/seasons/{id}/episodes")
    suspend fun getEpisodes(@Path("id") id : Int): List<Episodes>

    @GET("/search/shows")
    suspend fun searchShows(@Query("q") query : String): List<SearchSeries>

}
