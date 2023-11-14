package com.example.newsapp.presentation.search_headlines.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.domain.model.TopHeadlines

@Composable
fun SearchHeadlinesItem(article: Article) {

    AsyncImage(
        model = article.urlToImage,
        contentDescription = article.description,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.3f),
        placeholder = painterResource(id = R.drawable.loading),
        error = painterResource(id = R.drawable.error),
        fallback = painterResource(id = R.drawable.noresults),
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.height(10.dp))
    article.title?.let {
        Text(
            text = it,
            fontSize = 24.sp,
            color = Color.Black,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.Start) {
            article.source?.name?.let { Text(text = it) }

        }
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {

            IconButton(onClick = {


            }) {
                Icon(imageVector = Icons.Filled.Share, contentDescription = "Share News")
            }
            IconButton(onClick = {


            }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "More Options")
            }

        }

    }

}

