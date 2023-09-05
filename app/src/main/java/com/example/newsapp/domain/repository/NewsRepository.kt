package com.example.newsapp.domain.repository

import com.example.newsapp.data.remote.dto.TopHeadlinesDto

interface NewsRepository {

    suspend fun getTopHeadlines(country: String, apiKey: String): TopHeadlinesDto
}