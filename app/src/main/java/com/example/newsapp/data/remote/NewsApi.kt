package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.dto.TopHeadlinesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): TopHeadlinesDto

    @GET("v2/everything")
    suspend fun searchHeadlines(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): TopHeadlinesDto
}