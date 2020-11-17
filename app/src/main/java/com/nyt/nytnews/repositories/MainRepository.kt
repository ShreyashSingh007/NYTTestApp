package com.nyt.nytnews.repositories

import com.nyt.nytnews.database.NYTStoriesDao
import com.nyt.nytnews.models.Result
import com.nyt.nytnews.utility.RetrofitInstance

/**
 * This is data repository class responsible for storing/updating data
 * @author Shreyash Singh
 * @version 1.0
 */
class MainRepository(private val nytStoriesDao: NYTStoriesDao) {
    suspend fun getTopStories(): List<Result> {

        val body = RetrofitInstance.nytTopStoriesAPI.getTopStories()
        return body.body()!!.results
    }

    suspend fun storeData(data: List<Result>) {
        nytStoriesDao.insertAll(data)
    }

    suspend fun getOfflineData(): List<Result> {
        return nytStoriesDao.getAllStories()
    }

    suspend fun updateStory(result: Result) {
        nytStoriesDao.insert(result)
    }

    fun getDao() = nytStoriesDao
}