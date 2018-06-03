package com.github.fatihsokmen.codewars.data.remote.challengedetails

import com.google.gson.annotations.SerializedName

data class ChallengeDetailsDto(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("slug") val slug: String,
        @SerializedName("category") val category: String,
        @SerializedName("languages") val languages: List<String>,
        @SerializedName("description") val description: String,
        @SerializedName("tags") val tags: List<String>
)