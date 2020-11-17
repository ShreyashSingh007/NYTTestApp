package com.nyt.nytnews.models


import com.google.gson.annotations.SerializedName

/**
 * This is model class for the Multimedia object parsing
 * @author Shreyash Singh
 * @version 1.0
 */
data class Multimedia(
    @SerializedName("caption")
    val caption: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("format")
    val format: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("subtype")
    val subtype: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)