package com.example.retrofit.data.network

import com.example.retrofit.data.network.ApiModule.retrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiModule {

fun client() =
    OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

fun gson(): Gson = GsonBuilder().create()

 val retrofit : Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl("https://api.tvmaze.com")
        .client(client())
        .addConverterFactory(GsonConverterFactory.create(gson()))
        .build()
}
}
object ApiServiceRetrofit {
fun services(): ApiService =
    retrofit.create(ApiService::class.java)
}
