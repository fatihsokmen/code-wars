package com.github.fatihsokmen.codewars.datasource.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
        @PrimaryKey
        val userName: String,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "honor")
        val honor: Int,
        @ColumnInfo(name = "clan")
        val clan: String,
        @ColumnInfo(name = "skills")
        val skills: List<String>)


