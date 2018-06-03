package com.github.fatihsokmen.codewars.data.remote.search

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface UserSearchService {

    @GET("users/{username}")
    fun getUser(@Path("username") userName: String): Observable<UserDto>

}
