package com.github.fatihsokmen.codewars.datasource.mapper

import com.github.fatihsokmen.codewars.datasource.local.UserEntity
import com.github.fatihsokmen.codewars.datasource.remote.UserDto
import io.reactivex.functions.Function
import javax.inject.Inject

class UserDtoToEntityMapper @Inject constructor() : Function<UserDto, UserEntity> {

    override fun apply(dto: UserDto): UserEntity =
            UserEntity(
                    dto.userName, dto.name, dto.honor, dto.clan, dto.skills
            )
}
