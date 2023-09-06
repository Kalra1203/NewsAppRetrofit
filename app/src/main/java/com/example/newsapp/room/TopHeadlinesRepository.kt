package com.example.newsapp.room

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopHeadlinesRepository @Inject constructor(
    private val dao: TopHeadlinesDao
) {
    suspend fun insert(topHeadlinesEntity: TopHeadlinesEntity) = withContext(Dispatchers.IO) {
        dao.insert(topHeadlinesEntity)
    }

    fun getAllTopHeadlines(): Flow<List<TopHeadlinesEntity>> = dao.getAllTopHeadlines()

    suspend fun deleteTopHeadlines(topHeadlinesEntity: TopHeadlinesEntity) =
        withContext(Dispatchers.IO) {
            dao.deleteTopHeadlines(topHeadlinesEntity)
        }
}