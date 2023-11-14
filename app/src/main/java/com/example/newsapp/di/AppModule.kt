package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.common.Constants
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.presentation.search_headlines.SearchHeadlinesRepository
import com.example.newsapp.room.TopHeadlinesDao
import com.example.newsapp.room.TopHeadlinesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi): NewsRepository {
        return NewsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSearchHeadlineRepository(api: NewsApi) : SearchHeadlinesRepository{
        return SearchHeadlinesRepository(api)
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room.databaseBuilder(
        application,
        TopHeadlinesDatabase::class.java,
        "savedTopHeadlinesDatabase"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providesDao(topHeadlinesDatabase: TopHeadlinesDatabase): TopHeadlinesDao =
        topHeadlinesDatabase.getDao()
}