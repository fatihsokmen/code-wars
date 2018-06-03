package com.github.fatihsokmen.codewars.data.mapper.challenges

import com.github.fatihsokmen.codewars.data.CompletedChallengeDomain
import com.github.fatihsokmen.codewars.data.remote.challenges.CompletedChallengesDto
import io.reactivex.functions.Function
import javax.inject.Inject

class CompletedChallengesDtoToDomainMapper @Inject constructor()
    : Function<CompletedChallengesDto, List<CompletedChallengeDomain>> {

    override fun apply(dto: CompletedChallengesDto): List<CompletedChallengeDomain> {
        val challengeDomains = mutableListOf<CompletedChallengeDomain>()
        dto.data.forEach{
            challengeDomains.add(
                    CompletedChallengeDomain(it.id, it.name)
            )
        }
        return challengeDomains
    }
}
