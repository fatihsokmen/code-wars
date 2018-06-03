package com.github.fatihsokmen.codewars.data.mapper.challenges

import com.github.fatihsokmen.codewars.data.ChallengeDomain
import com.github.fatihsokmen.codewars.data.remote.challenges.CompletedChallengesResponseDto
import io.reactivex.functions.Function
import javax.inject.Inject

class CompletedChallengesDtoToDomainMapper @Inject constructor()
    : Function<CompletedChallengesResponseDto, List<ChallengeDomain>> {

    override fun apply(dto: CompletedChallengesResponseDto): List<ChallengeDomain> {
        val challengeDomains = mutableListOf<ChallengeDomain>()
        dto.data.forEach {
            challengeDomains.add(
                    ChallengeDomain(it.id, it.name)
            )
        }
        return challengeDomains
    }
}
