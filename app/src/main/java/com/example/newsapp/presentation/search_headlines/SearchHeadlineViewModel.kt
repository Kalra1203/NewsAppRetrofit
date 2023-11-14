package com.example.newsapp.presentation.search_headlines

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchHeadlineViewModel @Inject constructor(
    private val searchHeadlinesRepository: SearchHeadlinesRepository
) : ViewModel() {

    val list: MutableState<SearchHeadlineState> = mutableStateOf(SearchHeadlineState())

    fun getSearchHeadlines(q: String) = viewModelScope.launch {
        list.value = SearchHeadlineState(isLoading = true)
        try {


            val result = searchHeadlinesRepository.searchHeadlines(q)

            when (result) {
                is Resource.Success -> {
                    result.data?.articles.let {
                        list.value = SearchHeadlineState(topHeadlines = it ?: emptyList())
                    }


                }

                is Resource.Error -> {
                    list.value = SearchHeadlineState(error = "something went wrong!")
                }

                else -> {}
            }
        } catch (e: Exception) {
            list.value = SearchHeadlineState(error = "Something went wrong!")
        }

    }
}