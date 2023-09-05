package com.example.newsapp.domain.use_case

import android.util.Log
import com.example.newsapp.common.Resource
import com.example.newsapp.data.remote.dto.toTopHeadlines
import com.example.newsapp.domain.model.TopHeadlines
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(country: String, apiKey: String): Flow<Resource<List<TopHeadlines>>> =
        flow {
            try {
                emit(Resource.Loading<List<TopHeadlines>>())
                val topHeadlines = repository.getTopHeadlines(country, apiKey)
                emit(Resource.Success<List<TopHeadlines>>(topHeadlines.articles.map {
                    it.toTopHeadlines()
                }))

            } catch (e: HttpException) {
                emit(
                    Resource.Error<List<TopHeadlines>>(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
                Log.d("karan", e.localizedMessage ?: "unexpected error")
            } catch (e: IOException) {
                emit(Resource.Error<List<TopHeadlines>>("Couldn't reach server. Check your Internet Connection!"))
                Log.d("karan", e.message ?: "Check your internet")
            }

        }
}