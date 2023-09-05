package com.example.newsapp.presentation.saved_headlines

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.room.TopHeadlinesDao
import com.example.newsapp.room.TopHeadlinesEntity
import com.example.newsapp.room.TopHeadlinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedHeadlinesViewModelRoom @Inject constructor(
    private val topHeadlinesRepository: TopHeadlinesRepository,
    private val topHeadlinesDao: TopHeadlinesDao
) : ViewModel() {
     var topHeadlines: MutableState<List<TopHeadlinesEntity>> = mutableStateOf(listOf())

    fun insert(topHeadlinesEntity: TopHeadlinesEntity) {
        viewModelScope.launch {
            topHeadlinesRepository.insert(topHeadlinesEntity)
        }
    }
    init{
        getAllTopHeadlines()
    }

     fun getAllTopHeadlines() {
        viewModelScope.launch {
            topHeadlinesRepository.getAllTopHeadlines().catch { e ->
                Log.d("karan", e.message + "getAllTopHeadlinesRoom")

            }.collect() {
                topHeadlines.value = it
            }

        }
    }


    fun deleteTopHeadlines(topHeadlinesEntity: TopHeadlinesEntity) {
        viewModelScope.launch {
            try {
                topHeadlinesDao.deleteTopHeadlines(topHeadlinesEntity)
            } catch (e: Exception) {
                Log.d("karan", e.localizedMessage ?: "Exception in deleteTopHeadlines")
            }
        }
    }
}