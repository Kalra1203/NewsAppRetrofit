package com.example.newsapp.presentation.top_headlines

import com.example.newsapp.domain.model.TopHeadlines

data class TopHeadlinesState(
    val isLoading: Boolean = false,
    val topHeadlines: List<TopHeadlines> = emptyList(),
    val error: String = ""
)
