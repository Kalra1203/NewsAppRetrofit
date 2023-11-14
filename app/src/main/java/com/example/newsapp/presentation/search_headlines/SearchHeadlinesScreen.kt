package com.example.newsapp.presentation.search_headlines

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.presentation.search_headlines.components.SearchHeadlinesItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchHeadlinesScreen(searchHeadlineViewModel: SearchHeadlineViewModel = hiltViewModel()) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(8.dp)) {

            val query: MutableState<String> = remember {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = query.value,
                onValueChange = {
                    query.value = it
                    searchHeadlineViewModel.getSearchHeadlines(query.value)
                },
                enabled = true,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search ")
                })

            if (searchHeadlineViewModel.list.value.isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            if (searchHeadlineViewModel.list.value.error.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = searchHeadlineViewModel.list.value.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            if(searchHeadlineViewModel.list.value.topHeadlines.isEmpty()){
                Box {
                    Text(text = "data is null", modifier = Modifier.align(Alignment.Center))
                }
            }
            if(searchHeadlineViewModel.list.value.topHeadlines.isNotEmpty()){
                LazyColumn{
                    items(searchHeadlineViewModel.list.value.topHeadlines){
                        SearchHeadlinesItem(article = it)
                        
                    }
                }
            }

        }

    }
}