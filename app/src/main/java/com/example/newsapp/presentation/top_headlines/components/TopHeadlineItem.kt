package com.example.newsapp.presentation.top_headlines.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.newsapp.domain.model.TopHeadlines
import com.example.newsapp.presentation.detailed_screen.DetailedScreen
import com.example.newsapp.presentation.saved_headlines.SavedHeadlinesViewModelRoom
import com.example.newsapp.room.TopHeadlinesEntity


@Composable
fun TopHeadlineItem(
    topHeadlines: TopHeadlines,
    onItemClick: (TopHeadlines) -> Unit,
    roomViewModel: SavedHeadlinesViewModelRoom,
    navController: NavController

) {
    Column(modifier = Modifier.clickable {
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
    }) {
        val context = LocalContext.current
        var filledStar by remember {
            mutableStateOf(false)
        }
        AsyncImage(
            model = topHeadlines.urlToImage,
            contentDescription = topHeadlines.description,
            placeholder = painterResource(id = R.drawable.loading),
            error = painterResource(id = R.drawable.error),
            fallback = painterResource(id = R.drawable.noresults),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.25f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        topHeadlines.title?.let {
            Text(
                text = it,
                fontSize = 24.sp,
                color = Color.Black,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Row(horizontalArrangement = Arrangement.Start) {
                topHeadlines.source?.name?.let { Text(text = it, color = Color.LightGray) }

            }
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = {
                    filledStar = true

                    roomViewModel.insert(
                        TopHeadlinesEntity(
                            title = topHeadlines.title!!,
                            content = topHeadlines.content,
                            description = topHeadlines.description,
                            publishedAt = topHeadlines.publishedAt,
                            urlToImage = topHeadlines.urlToImage,
                            source = topHeadlines.source?.name,
                            url = topHeadlines.url

                        )
                    )

                    Log.d("karan", "Saved Successful")
                    Toast.makeText(context, "Insertion Successful", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = if (filledStar) painterResource(id = R.drawable.filled_star) else painterResource(
                            id = R.drawable.star
                        ), contentDescription = "Save News", modifier = Modifier.height(22.dp)
                    )
                }
                IconButton(onClick = {
                    context.shareLink(topHeadlines.url!!)


                }) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "Share News")
                }
//                IconButton(onClick = { }) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.chrome),
//                        contentDescription = "open in chrome",
//                        modifier = Modifier.height(22.dp)
//
//
//                    )
//                }
                val imageModifier = Modifier
                    .size(22.dp)
                    .align(Alignment.CenterVertically)

                Image(
                    painter = painterResource(id = R.drawable.chrome),
                    contentDescription = "open in chrome",
                    contentScale = ContentScale.Crop,
                    modifier = imageModifier.clickable { context.shareLinkChrome(topHeadlines.url!!) },

                )

            }

        }
    }
}

fun Context.shareLink(url: String) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Context.shareLinkChrome(uri: String) {
    val uriParsed = Uri.parse(uri)
    val sendIntent = Intent(Intent.ACTION_VIEW, uriParsed)
    try {
        startActivity(sendIntent)
    } catch (e: ActivityNotFoundException) {
        // Define what your app should do if no activity can handle the intent.
        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
    }
}
