package com.example.newsapp.presentation.detailed_screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.R

@Composable
fun DetailedItem(detailedScreen: DetailedScreen) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        item {
            detailedScreen.title?.let { Text(text = it, fontSize = 24.sp, color = Color.Black) }
            Spacer(modifier = Modifier.height(10.dp))

        }
        item {
            detailedScreen.description?.let { Text(text = it, fontSize = 20.sp) }


        }


        item {
            AsyncImage(
                model = detailedScreen.urlToImage,
                contentDescription = detailedScreen.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.3f),
                placeholder = painterResource(id = R.drawable.loading),
                error = painterResource(id = R.drawable.error),
                fallback = painterResource(id = R.drawable.noresults),
                contentScale = ContentScale.Crop

            )
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            detailedScreen.content?.let { Text(text = it, fontSize = 22.sp) }
            Spacer(modifier = Modifier.height(10.dp))
        }


    }

}