package com.example.newsapp.presentation.saved_headlines.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.room.TopHeadlinesEntity

@Composable
fun SavedHeadlinesItem(topHeadlines: TopHeadlinesEntity) {
    Column {
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
                    Icon(imageVector = Icons.Filled.Star, contentDescription = "Save News")
                }
                IconButton(onClick = {

                }) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "Share News")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "More Options")
                }

            }

        }
    }

}

