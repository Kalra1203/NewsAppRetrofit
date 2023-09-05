package com.example.newsapp.data.repository

import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.dto.TopHeadlinesDto
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {
    override suspend fun getTopHeadlines(country: String, apiKey: String): TopHeadlinesDto {
        return api.getTopHeadlines(country, apiKey)
    }
}