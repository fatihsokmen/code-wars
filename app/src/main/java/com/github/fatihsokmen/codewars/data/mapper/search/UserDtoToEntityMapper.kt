package com.github.fatihsokmen.codewars.data.mapper.search

import com.github.fatihsokmen.codewars.data.local.UserEntity
import com.github.fatihsokmen.codewars.data.remote.search.UserDto
import io.reactivex.functions.Function
import javax.inject.Inject

class UserDtoToEntityMapper @Inject constructor() : Function<UserDto, UserEntity> {

    override fun apply(dto: UserDto): UserEntity =
            UserEntity(
                    dto.userName, dto.name, dto.honor, dto.clan,
                    dto.leaderboardPosition, dto.skills, dto.ranks
            )
}
