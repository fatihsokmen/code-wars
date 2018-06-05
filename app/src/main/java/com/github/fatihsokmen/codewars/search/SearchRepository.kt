package com.github.fatihsokmen.codewars.search

import com.github.fatihsokmen.codewars.data.UserDomain
import com.github.fatihsokmen.codewars.data.local.UserDao
import com.github.fatihsokmen.codewars.data.mapper.search.UserDtoToDomainMapper
import com.github.fatihsokmen.codewars.data.mapper.search.UserDtoToEntityMapper
import com.github.fatihsokmen.codewars.data.mapper.search.UserEntityToDomainMapper
import com.github.fatihsokmen.codewars.data.remote.search.UserSearchService
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class SearchRepository @Inject constructor(
        private val userDao: UserDao,
        private val userSearchService: UserSearchService,
        private val userDtoToEntityMapper: UserDtoToEntityMapper,
        private val userEntityToDomainMapper: UserEntityToDomainMapper,
        private val userDtoToDomainMapper: UserDtoToDomainMapper): ISearchRepository {

    override fun getRecent(): Flowable<List<UserDomain>> =
            userDao.getUsers().map { entities ->
                userEntityToDomainMapper.apply(entities)
            }

    override fun searchUser(userName: String): Observable<UserDomain> =
            userSearchService.getUser(userName).doOnNext { dto ->
                userDao.insert(userDtoToEntityMapper.apply(dto))
            }.map { dto ->
                userDtoToDomainMapper.apply(dto)
            }
}

