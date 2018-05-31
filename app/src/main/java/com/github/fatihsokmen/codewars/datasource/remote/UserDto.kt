package com.github.fatihsokmen.codewars.datasource.remote

import com.google.gson.annotations.SerializedName


data class UserDto(
        @SerializedName("username") val userName: String,
        @SerializedName("name") val name: String,
        @SerializedName("honor") val honor: Int,
        @SerializedName("clan") val clan: String,
        @SerializedName("skills") val skills: List<String>
)
