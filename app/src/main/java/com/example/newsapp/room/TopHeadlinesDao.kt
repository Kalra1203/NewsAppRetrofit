package com.example.newsapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.common.Resource
import kotlinx.coroutines.flow.Flow

@Dao
interface TopHeadlinesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(topHeadlinesEntity: TopHeadlinesEntity)

    @Query("Select * from savedTopHeadlines")
    fun getAllTopHeadlines(): Flow<List<TopHeadlinesEntity>>

    @Delete()
    suspend fun deleteTopHeadlines(topHeadlinesEntity: TopHeadlinesEntity)
}