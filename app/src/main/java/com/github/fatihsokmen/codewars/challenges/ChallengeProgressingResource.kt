package com.github.fatihsokmen.codewars.challenges

class ChallengeProgressingResource private constructor(
        val status: Status,
        val errorMessage: String?) {

    companion object {
        fun error(errorMessage: String?): ChallengeProgressingResource {
            return ChallengeProgressingResource(Status.ERROR, errorMessage)
        }

        fun loading(): ChallengeProgressingResource {
            return ChallengeProgressingResource(Status.LOADING, null)
        }

        fun success(): ChallengeProgressingResource {
            return ChallengeProgressingResource(Status.SUCCESS, null)
        }

    }

    enum class Status {
        SUCCESS, ERROR, LOADING
    }
}