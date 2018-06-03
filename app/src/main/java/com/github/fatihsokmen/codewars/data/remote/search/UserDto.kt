package com.github.fatihsokmen.codewars.data.remote.search

import com.google.gson.annotations.SerializedName

data class UserDto(
        @SerializedName("username") val userName: String,
        @SerializedName("name") val name: String?,
        @SerializedName("honor") val honor: Int,
        @SerializedName("clan") val clan: String?,
        @SerializedName("leaderboardPosition") val leaderboardPosition: String,
        @SerializedName("skills") val skills: List<String>?,
        @SerializedName("ranks") val ranks: Ranks
)

data class Ranks(
        @SerializedName("overall") val overall: Rank,
        @SerializedName("languages") val languages: Map<String, Rank>
)

data class Rank(
        @SerializedName("rank") val rank: Int,
        @SerializedName("name") val name: String,
        @SerializedName("color") val color: String,
        @SerializedName("score") val score: Int
)

