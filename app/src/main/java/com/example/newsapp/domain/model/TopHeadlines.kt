package com.example.newsapp.domain.model

import com.example.newsapp.data.remote.dto.Source

data class TopHeadlines(

    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val urlToImage: String?,
    val source: Source?,
    val url: String?,

    )
