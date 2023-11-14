package com.example.newsapp.presentation.saved_headlines.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.presentation.detailed_screen.DetailedScreen
import com.example.newsapp.presentation.saved_headlines.SavedHeadlinesViewModelRoom
import com.example.newsapp.presentation.top_headlines.components.shareLink
import com.example.newsapp.room.TopHeadlinesEntity
import kotlinx.coroutines.launch

@Composable
fun SavedHeadlinesItem(
    topHeadlines: TopHeadlinesEntity,
    topHeadlinesViewModelRoom: SavedHeadlinesViewModelRoom,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    var openDialog by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier
        .clickable {
            val headline = DetailedScreen(
                content = topHeadlines.content,
                description = topHeadlines.description,
                publishedAt = topHeadlines.publishedAt,
                title = topHeadlines.title,
                urlToImage = topHeadlines.urlToImage,
                url = topHeadlines.url
            )
            navController.currentBackStackEntry?.savedStateHandle?.set("headline", headline)
            navController.navigate("detailedScreen")
        }
    ) {
        val context = LocalContext.current


        AsyncImage(
            model = topHeadlines.urlToImage,
            contentDescription = topHeadlines.description,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.3f),
            placeholder = painterResource(id = R.drawable.loading),
            error = painterResource(id = R.drawable.error),
            fallback = painterResource(id = R.drawable.noresults),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = topHeadlines.title,
            fontSize = 24.sp,
            color = Color.Black,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Start) {
                topHeadlines.source?.let { Text(text = it, color = Color.LightGray) }

            }
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {

                IconButton(onClick = {
                    context.shareLink(topHeadlines.url!!)


                }) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "Share News")
                }
                IconButton(onClick = {
                    openDialog = true


                }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "More Options")
                }

            }

        }
    }
    if (openDialog) {
        AlertDialog(

            onDismissRequest = { openDialog = false },


            title = { Text(text = "Delete", color = Color.White) },


            text = { Text("Are you sure you want to delete this item?", color = Color.White) },


            confirmButton = {

                TextButton(onClick = {
                    coroutineScope.launch {
                        topHeadlinesViewModelRoom.deleteTopHeadlines(topHeadlines)
                    }
                    openDialog = false


                }) {

                    Text("Confirm", color = Color.White)
                }
            },

            dismissButton = {

                TextButton(onClick = {
                    openDialog = false
                }) {
                    Text("Dismiss", color = Color.White)
                }
            }, containerColor = MaterialTheme.colorScheme.primaryContainer

        )
    }
}



