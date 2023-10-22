package com.example.newsapp.presentation.saved_headlines

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.presentation.saved_headlines.components.SavedHeadlinesItem

@Composable
fun SavedHeadlinesScreen(
    topHeadlinesViewModelRoom: SavedHeadlinesViewModelRoom,
    navController: NavController
) {
    if (topHeadlinesViewModelRoom.topHeadlines.value.isEmpty()) {
        Text(text = "Empty List")
    }
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 6.dp)) {
        items(topHeadlinesViewModelRoom.topHeadlines.value) {
            SavedHeadlinesItem(topHeadlines = it, topHeadlinesViewModelRoom, navController)
        }
    }

}
