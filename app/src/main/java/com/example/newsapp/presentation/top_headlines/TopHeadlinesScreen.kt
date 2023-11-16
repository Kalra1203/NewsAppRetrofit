package com.example.newsapp.presentation.top_headlines

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.presentation.top_headlines.components.TopHeadlineItem
import com.example.newsapp.presentation.saved_headlines.SavedHeadlinesViewModelRoom

@Composable
fun TopHeadlinesScreen(
    navController: NavController,
    viewModel: TopHeadlineViewModel = hiltViewModel(),
    roomViewModel: SavedHeadlinesViewModelRoom = hiltViewModel()
) {

    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp)) {
            items(state.topHeadlines) { topHeadline ->
                TopHeadlineItem(
                    topHeadlines = topHeadline,
                    onItemClick = {},
                    roomViewModel,
                    navController
                )
            }

        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}


