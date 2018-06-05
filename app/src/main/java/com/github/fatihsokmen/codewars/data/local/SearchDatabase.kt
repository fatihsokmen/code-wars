package com.github.fatihsokmen.codewars.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

@Database(entities = [UserEntity::class], version = SearchDatabase.VERSION, exportSchema = false)
@TypeConverters(StringListTypeConverter::class, RanksTypeConverter::class, DateTypeConverter::class)
abstract class SearchDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val VERSION = 1
    }
}