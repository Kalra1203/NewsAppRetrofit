package com.example.newsapp.presentation.detailed_screen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DetailedScreen(

    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val urlToImage: String?

) : Parcelable