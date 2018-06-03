package com.github.fatihsokmen.codewars.challenges

class ProgressResource private constructor(
        val status: Status,
        val errorMessage: String?) {

    companion object {
        fun error(errorMessage: String?): ProgressResource {
            return ProgressResource(Status.ERROR, errorMessage)
        }

        fun loading(): ProgressResource {
            return ProgressResource(Status.LOADING, null)
        }
    }

    enum class Status {
        ERROR, LOADING
    }
}