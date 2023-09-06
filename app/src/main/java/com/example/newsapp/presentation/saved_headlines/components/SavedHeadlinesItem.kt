package com.example.newsapp.presentation.saved_headlines.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.presentation.saved_headlines.SavedHeadlinesViewModelRoom
import com.example.newsapp.room.TopHeadlinesEntity
import kotlinx.coroutines.launch
import okhttp3.internal.notify

@Composable
fun SavedHeadlinesItem(
    topHeadlines: TopHeadlinesEntity,
    topHeadlinesViewModelRoom: SavedHeadlinesViewModelRoom
) {
    Column {
        var coroutieScope = rememberCoroutineScope()

        AsyncImage(model = topHeadlines.urlToImage, contentDescription = topHeadlines.description)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = topHeadlines.title, fontSize = 24.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Start) {
                topHeadlines.source?.let { Text(text = it, color = Color.LightGray) }

            }
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {

                IconButton(onClick = {

                }) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "Share News")
                }
                IconButton(onClick = {
                    Log.d("karan", topHeadlinesViewModelRoom.topHeadlines.value.size.toString())
                    coroutieScope.launch {
                        topHeadlinesViewModelRoom.deleteTopHeadlines(topHeadlines)
                    }
                    Log.d("karan", topHeadlinesViewModelRoom.topHeadlines.value.size.toString())

                }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "More Options")
                }

            }

        }
    }

}

