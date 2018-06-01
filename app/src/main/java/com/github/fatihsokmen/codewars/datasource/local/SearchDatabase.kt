package com.github.fatihsokmen.codewars.datasource.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

@Database(entities = [UserEntity::class], version = SearchDatabase.VERSION, exportSchema = false)
@TypeConverters(StringListTypeConverters::class, RanksTypeConverters::class)
abstract class SearchDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val VERSION = 1
    }
}