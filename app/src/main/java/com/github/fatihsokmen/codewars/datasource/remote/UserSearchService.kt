package com.github.fatihsokmen.codewars.datasource.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface UserSearchService {

    @GET("users/{username}")
    fun getUser(@Path("username") userName: String): Single<UserDto>

}
