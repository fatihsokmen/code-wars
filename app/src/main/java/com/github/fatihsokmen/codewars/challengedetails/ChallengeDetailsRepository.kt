package com.github.fatihsokmen.codewars.challengedetails

import com.github.fatihsokmen.codewars.data.ChallengeDetailsDomain
import com.github.fatihsokmen.codewars.data.mapper.challenges.ChallengeDetailsDtoToDomainMapper
import com.github.fatihsokmen.codewars.data.remote.challenges.ChallengeApiService
import io.reactivex.Observable
import javax.inject.Inject

class ChallengeDetailsRepository @Inject constructor(
        private val challengeApiService: ChallengeApiService,
        private val challengeDetailsDtoToDomainMapper: ChallengeDetailsDtoToDomainMapper) {

    fun getChallengeDetails(challengeId: String): Observable<ChallengeDetailsDomain> =
            challengeApiService.getChallengeDetails(challengeId)
                    .map { dto ->
                        challengeDetailsDtoToDomainMapper.apply(dto)
                    }
}

