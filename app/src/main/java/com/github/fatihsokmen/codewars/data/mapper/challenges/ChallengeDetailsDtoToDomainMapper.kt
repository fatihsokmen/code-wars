package com.github.fatihsokmen.codewars.data.mapper.challenges

import com.github.fatihsokmen.codewars.data.ChallengeDetailsDomain
import com.github.fatihsokmen.codewars.data.remote.challengedetails.ChallengeDetailsDto
import io.reactivex.functions.Function
import javax.inject.Inject

class ChallengeDetailsDtoToDomainMapper @Inject constructor()
    : Function<ChallengeDetailsDto, ChallengeDetailsDomain> {

    override fun apply(dto: ChallengeDetailsDto): ChallengeDetailsDomain =
            ChallengeDetailsDomain(
                    dto.id, dto.name, dto.slug, dto.category, dto.languages, dto.description, dto.tags
            )
}
