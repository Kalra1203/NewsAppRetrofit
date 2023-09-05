package com.example.newsapp.data.remote.dto

data class Source(
    val id: String?,
    val name: String?
)

fun Source.toName(): String? {
    return name
}