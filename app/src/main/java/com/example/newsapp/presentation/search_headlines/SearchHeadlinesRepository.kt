package com.example.newsapp.presentation.search_headlines

import com.example.newsapp.common.Constants
import com.example.newsapp.common.Resource
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.data.remote.dto.TopHeadlinesDto
import javax.inject.Inject

class SearchHeadlinesRepository @Inject constructor(private val newsApi: NewsApi) {

    suspend fun searchHeadlines(q: String): Resource<TopHeadlinesDto> {
        return try {
            val result = newsApi.searchHeadlines(q, apiKey = Constants.API_KEY)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())

        }

    }
}