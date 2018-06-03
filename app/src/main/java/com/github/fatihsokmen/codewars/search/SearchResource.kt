package com.github.fatihsokmen.codewars.search

class SearchResource<T> private constructor(
        val status: Status,
        val data: T?,
        val errorMessage: String?) {

    companion object {
        fun <T> success(data: T?): SearchResource<T> {
            return SearchResource(Status.SUCCESS, data, null)
        }

        fun <T> error(errorMessage: String?): SearchResource<T> {
            return SearchResource(Status.ERROR, null, errorMessage)
        }

        fun <T> loading(): SearchResource<T> {
            return SearchResource(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS, ERROR, LOADING
}