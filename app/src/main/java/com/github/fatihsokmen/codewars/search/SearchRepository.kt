package com.github.fatihsokmen.codewars.search

import com.github.fatihsokmen.codewars.datasource.UserDomain
import com.github.fatihsokmen.codewars.datasource.local.UserDao
import com.github.fatihsokmen.codewars.datasource.mapper.UserDtoToDomainMapper
import com.github.fatihsokmen.codewars.datasource.mapper.UserDtoToEntityMapper
import com.github.fatihsokmen.codewars.datasource.mapper.UserEntityToDomainMapper
import com.github.fatihsokmen.codewars.datasource.remote.UserSearchService
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class SearchRepository @Inject constructor(
        private val userDao: UserDao,
        private val userSearchService: UserSearchService,
        private val userDtoToEntityMapper: UserDtoToEntityMapper,
        private val userEntityToDomainMapper: UserEntityToDomainMapper,
        private val userDtoToDomainMapper: UserDtoToDomainMapper) {

    fun getRecent(): Flowable<List<UserDomain>> =
            userDao.getUsers().map { entities ->
                userEntityToDomainMapper.apply(entities)
            }

    fun searchUser(userName: String): Single<UserDomain> =
            userSearchService.getUser(userName).doOnSuccess { dto ->
                userDao.insert(userDtoToEntityMapper.apply(dto))
            }.map { dto ->
                userDtoToDomainMapper.apply(dto)
            }
}
