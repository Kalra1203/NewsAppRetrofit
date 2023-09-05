package com.example.newsapp.data.remote.dto

data class TopHeadlinesDto(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)