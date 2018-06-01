package com.github.fatihsokmen.codewars.datasource.local

import android.arch.persistence.room.TypeConverter
import com.github.fatihsokmen.codewars.datasource.remote.Ranks
import com.google.gson.Gson

object RanksTypeConverters {

    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun toString(values: Ranks): String {
        return gson.toJson(values)
    }

    @TypeConverter
    @JvmStatic
    fun toRanks(json: String): Ranks {
        return gson.fromJson(json, Ranks::class.java)
    }
}