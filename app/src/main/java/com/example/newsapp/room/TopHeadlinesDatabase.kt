package com.example.newsapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TopHeadlinesEntity::class], version = 1, exportSchema = false)
abstract class TopHeadlinesDatabase : RoomDatabase() {
    abstract fun getDao(): TopHeadlinesDao
}