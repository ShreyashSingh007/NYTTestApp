package com.nyt.nytnews.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nyt.nytnews.models.Multimedia

/**
 * This is converter class used for converting the data to desired format.
 * @author Shreyash Singh
 * @version 1.0
 */
class Converters {

    @TypeConverter
    fun fromMultimedia(dataList: List<Multimedia>?): String? {
        if (dataList == null)
            return null
        val gson = Gson()
        val type = object : TypeToken<List<Multimedia>>() {}.type
        return gson.toJson(dataList, type)
    }

    @TypeConverter
    fun toMultimedia(data: String): List<Multimedia> {
        val gson = Gson()
        val type = object : TypeToken<List<Multimedia>>() {}.type
        return gson.fromJson(data, type)
    }
}