package com.github.fatihsokmen.codewars.data.mapper.search

import com.github.fatihsokmen.codewars.data.UserDomain
import com.github.fatihsokmen.codewars.data.remote.search.UserDto
import io.reactivex.functions.Function
import javax.inject.Inject

class UserDtoToDomainMapper @Inject constructor() : Function<UserDto, UserDomain> {

    override fun apply(dto: UserDto): UserDomain =
            UserDomain(
                    dto.userName, dto.name, dto.honor, dto.clan,
                    dto.leaderboardPosition, dto.skills, dto.ranks
            )
}
