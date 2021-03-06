package com.nyt.nytnews.models


import com.google.gson.annotations.SerializedName

/**
 * This is model class for the Stories object parsing
 * @author Shreyash Singh
 * @version 1.0
 */
data class NYTStoriesModel(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("section")
    val section: String,
    @SerializedName("status")
    val status: String
)