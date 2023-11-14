package com.example.newsapp.presentation.search_headlines

import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.data.remote.dto.TopHeadlinesDto
import com.example.newsapp.domain.model.TopHeadlines

data class SearchHeadlineState(
    val isLoading: Boolean = false,
    val topHeadlines: List<Article> = emptyList(),
    val error: String = ""
)
