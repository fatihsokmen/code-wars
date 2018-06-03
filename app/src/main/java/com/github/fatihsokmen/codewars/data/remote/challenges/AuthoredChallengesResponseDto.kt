package com.github.fatihsokmen.codewars.data.remote.challenges

import com.google.gson.annotations.SerializedName

data class AuthoredChallengesResponseDto(
        @SerializedName("data") val data: List<CompletedChallengeDto>
)

data class AuthoredChallengeDto(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String
)