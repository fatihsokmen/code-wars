package com.github.fatihsokmen.codewars.challengedetails

class ChallengeDetailsResource<T> private constructor(
        val status: Status,
        val data: T?,
        val errorMessage: String?) {

    companion object {
        fun <T> success(data: T?): ChallengeDetailsResource<T> {
            return ChallengeDetailsResource(Status.SUCCESS, data, null)
        }

        fun <T> error(errorMessage: String?): ChallengeDetailsResource<T> {
            return ChallengeDetailsResource(Status.ERROR, null, errorMessage)
        }

        fun <T> loading(): ChallengeDetailsResource<T> {
            return ChallengeDetailsResource(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS, ERROR, LOADING
}
