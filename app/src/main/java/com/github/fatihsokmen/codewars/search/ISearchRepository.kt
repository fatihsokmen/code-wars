package com.github.fatihsokmen.codewars.search

import com.github.fatihsokmen.codewars.data.UserDomain
import io.reactivex.Flowable
import io.reactivex.Observable

interface ISearchRepository {

    fun getRecent(): Flowable<List<UserDomain>>

    fun searchUser(userName: String): Observable<UserDomain>
}

