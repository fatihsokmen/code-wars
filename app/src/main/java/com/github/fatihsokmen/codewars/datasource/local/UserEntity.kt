package com.github.fatihsokmen.codewars.datasource.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.github.fatihsokmen.codewars.datasource.remote.Ranks

@Entity(tableName = "User")
data class UserEntity(
        @PrimaryKey
        val userName: String,
        @ColumnInfo(name = "name")
        val name: String? = null,
        @ColumnInfo(name = "honor")
        val honor: Int,
        @ColumnInfo(name = "clan")
        val clan: String?,
        @ColumnInfo(name = "leaderboardPosition")
        val leaderboardPosition: String,
        @ColumnInfo(name = "skills")
        val skills: List<String>? = null,
        @ColumnInfo(name = "ranks")
        val ranks: Ranks
)


