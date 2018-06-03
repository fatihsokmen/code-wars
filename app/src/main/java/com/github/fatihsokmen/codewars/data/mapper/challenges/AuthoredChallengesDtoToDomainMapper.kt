package com.github.fatihsokmen.codewars.data.mapper.challenges

import com.github.fatihsokmen.codewars.data.ChallengeDomain
import com.github.fatihsokmen.codewars.data.remote.challenges.AuthoredChallengesResponseDto
import io.reactivex.functions.Function
import javax.inject.Inject

class AuthoredChallengesDtoToDomainMapper @Inject constructor()
    : Function<AuthoredChallengesResponseDto, List<ChallengeDomain>> {

    override fun apply(dto: AuthoredChallengesResponseDto): List<ChallengeDomain> {
        val challengeDomains = mutableListOf<ChallengeDomain>()
        dto.data.forEach {
            challengeDomains.add(
                    ChallengeDomain(it.id, it.name)
            )
        }
        return challengeDomains
    }
}
