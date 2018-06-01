package com.github.fatihsokmen.codewars.datasource.mapper

import com.github.fatihsokmen.codewars.datasource.UserDomain
import com.github.fatihsokmen.codewars.datasource.remote.UserDto
import io.reactivex.functions.Function
import javax.inject.Inject

class UserDtoToDomainMapper @Inject constructor() : Function<UserDto, UserDomain> {

    override fun apply(dto: UserDto): UserDomain =
            UserDomain(
                    dto.userName, dto.name, dto.honor, dto.clan,
                    dto.leaderboardPosition, dto.skills, dto.ranks
            )
}
