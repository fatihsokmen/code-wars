package com.github.fatihsokmen.codewars.datasource.local

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson

object StringListTypeConverters {

    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun toString(values: List<String>?): String? {
        return values?.let {
            gson.toJson(it)
        }
    }

    @TypeConverter
    @JvmStatic
    fun toList(json: String?): List<String>? {
        return json?.let {
            gson.fromJson(it, Array<String>::class.java).toList()
        } ?: listOf()
    }
}