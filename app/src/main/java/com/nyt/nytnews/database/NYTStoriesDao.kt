package com.nyt.nytnews.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nyt.nytnews.models.Result

/**
 * This is Data access objects. It defines all the database interactions and transactions
 * @author Shreyash Singh
 * @version 1.0
 */
@Dao
interface NYTStoriesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(data: List<Result>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: Result)

    @Query(value = "SELECT * FROM nyt_stories")
    suspend fun getAllStories(): List<Result>

    @Query(value = "SELECT * FROM nyt_stories WHERE bookmarked = 1")
    suspend fun getBookMarks(): List<Result>

    @Query(value = "SELECT shortUrl FROM nyt_stories WHERE bookmarked = 1")
    suspend fun getPrimaryKeys(): List<String>
}