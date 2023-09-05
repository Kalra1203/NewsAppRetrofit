package com.example.newsapp.presentation.top_headlines

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.common.Constants
import com.example.newsapp.common.Resource
import com.example.newsapp.domain.use_case.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(TopHeadlinesState())
    val state: State<TopHeadlinesState> = _state

    init {
        val country = savedStateHandle.get<String>(Constants.PARAM_COUNTRY_NAME) ?: "in"
        val apiKey =
            savedStateHandle.get<String>(Constants.API_KEY) ?: "e9ff3e58a1c542e9bb2a491d16877160"

        getTopHeadlines(country, apiKey)
    }

    private fun getTopHeadlines(country: String, apiKey: String) {
        getTopHeadlinesUseCase(country, apiKey).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = TopHeadlinesState(topHeadlines = result.data ?: emptyList())

                }

                is Resource.Error -> {
                    _state.value =
                        TopHeadlinesState(error = result.message ?: "An unexpected Error occurred")

                }

                is Resource.Loading -> {
                    _state.value = TopHeadlinesState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}
