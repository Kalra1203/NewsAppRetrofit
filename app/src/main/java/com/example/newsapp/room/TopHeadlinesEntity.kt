package com.example.newsapp.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.data.remote.dto.Source

@Entity(tableName = "savedTopHeadlines")
data class TopHeadlinesEntity(
    @PrimaryKey
    val title: String,
    val content: String? = "default content",
    val description: String? = "default description",
    val publishedAt: String? = "default published",
    val urlToImage: String? = "default url",
    val source: String? = "default source"

)
