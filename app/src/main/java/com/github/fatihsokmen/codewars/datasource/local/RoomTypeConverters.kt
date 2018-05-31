package com.github.fatihsokmen.codewars.datasource.local

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson

object RoomTypeConverters {

    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun toString(values: List<String>): String {
        return gson.toJson(values)
    }

    @TypeConverter
    @JvmStatic
    fun toList(json: String): List<String> {
        return gson.fromJson(json, Array<String>::class.java).toList()
    }
}