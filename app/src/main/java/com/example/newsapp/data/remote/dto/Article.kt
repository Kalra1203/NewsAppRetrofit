package com.example.newsapp.data.remote.dto

import com.example.newsapp.domain.model.TopHeadlines

data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

fun Article.toTopHeadlines(): TopHeadlines {
    return TopHeadlines(
        content = content,
        description = description,
        publishedAt = publishedAt,
        title = title,
        urlToImage = urlToImage,
        source = source,

        )
}