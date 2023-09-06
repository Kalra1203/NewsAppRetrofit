package com.example.newsapp.presentation.top_headlines.components

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newsapp.domain.model.TopHeadlines
import com.example.newsapp.presentation.saved_headlines.SavedHeadlinesViewModelRoom
import com.example.newsapp.room.TopHeadlinesEntity


@Composable
fun TopHeadlineItem(
    topHeadlines: TopHeadlines,
    onItemClick: (TopHeadlines) -> Unit,
    roomViewModel: SavedHeadlinesViewModelRoom,
    navController: NavController

) {
    Column {
        val context = LocalContext.current
        AsyncImage(
            model = topHeadlines.urlToImage,
            contentDescription = topHeadlines.description
        )
        Spacer(modifier = Modifier.height(10.dp))
        topHeadlines.title?.let { Text(text = it, fontSize = 24.sp, color = Color.Black) }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Start) {
                topHeadlines.source?.name?.let { Text(text = it, color = Color.LightGray) }

            }
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = {

                    roomViewModel.insert(
                        TopHeadlinesEntity(
                            title = topHeadlines.title!!,
                            content = topHeadlines.content,
                            description = topHeadlines.description,
                            publishedAt = topHeadlines.publishedAt,
                            urlToImage = topHeadlines.urlToImage,
                            source = topHeadlines.source?.name

                        )
                    )

                    Log.d("karan", "Saved Successful")
                    Toast.makeText(context, "Insertion Successful", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = "Save News")
                }
                IconButton(onClick = {


                }) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "Share News")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "More Options"
                    )
                }

            }

        }
    }
}
