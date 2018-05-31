package com.github.fatihsokmen.codewars.datasource.mapper

import com.github.fatihsokmen.codewars.datasource.UserDomain
import com.github.fatihsokmen.codewars.datasource.local.UserEntity
import io.reactivex.functions.Function
import javax.inject.Inject

class UserEntityToDomainMapper @Inject constructor() : Function<List<UserEntity>, List<UserDomain>> {

    override fun apply(entities: List<UserEntity>): List<UserDomain> {
        val users = mutableListOf<UserDomain>()
        entities.forEach { entity ->
            users.add(map(entity))
        }
        return users
    }

    private fun map(entity: UserEntity) =
            UserDomain(
                    userName = entity.userName,
                    name = entity.name,
                    honor = entity.honor,
                    clan = entity.clan,
                    skills = entity.skills
            )

}
