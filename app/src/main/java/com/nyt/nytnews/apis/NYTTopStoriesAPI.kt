package com.nyt.nytnews.apis

import com.nyt.nytnews.models.NYTStoriesModel
import com.nyt.nytnews.utility.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is interface for TopStories API
 * @author Shreyash Singh
 * @version 1.0
 */

interface NYTTopStoriesAPI {
    @GET("svc/topstories/v2/arts.json")
    suspend fun getTopStories(@Query("api-key") key: String = Constants.API_KEY): Response<NYTStoriesModel>
}