package com.example.newsapp.presentation.saved_headlines

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.newsapp.presentation.saved_headlines.components.SavedHeadlinesItem

@Composable
fun SavedHeadlinesScreen(topHeadlinesViewModelRoom: SavedHeadlinesViewModelRoom) {
    if (topHeadlinesViewModelRoom.topHeadlines.value.isEmpty()) {
        Text(text = "Empty List")
    }
    LazyColumn {
        items(topHeadlinesViewModelRoom.topHeadlines.value) {
            SavedHeadlinesItem(topHeadlines = it)
        }
    }

}
