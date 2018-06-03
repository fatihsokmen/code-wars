package com.github.fatihsokmen.codewars.data.remote.challenges

import com.google.gson.annotations.SerializedName

data class CompletedChallengesDto(
        @SerializedName("totalPages") val totalPages: Int,
        @SerializedName("totalItems") val totalItems: Int,
        @SerializedName("data") val data: List<CompletedChallengeDto>
)

data class CompletedChallengeDto(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String
)