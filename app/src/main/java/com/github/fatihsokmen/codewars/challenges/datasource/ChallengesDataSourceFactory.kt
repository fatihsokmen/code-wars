package com.github.fatihsokmen.codewars.challenges.datasource

import android.arch.paging.DataSource
import com.github.fatihsokmen.codewars.challenges.Flow
import com.github.fatihsokmen.codewars.challenges.Flow.AUTHORED_CHALLENGES
import com.github.fatihsokmen.codewars.challenges.Flow.COMPLETED_CHALLENGES
import com.github.fatihsokmen.codewars.data.ChallengeDomain
import com.github.fatihsokmen.codewars.data.mapper.challenges.AuthoredChallengesDtoToDomainMapper
import com.github.fatihsokmen.codewars.data.mapper.challenges.CompletedChallengesDtoToDomainMapper
import com.github.fatihsokmen.codewars.data.remote.challenges.ChallengesApiService
import javax.inject.Inject

class ChallengesDataSourceFactory @Inject constructor(
        private val userName: String,
        private val flow: Flow,
        private val challengesApiService: ChallengesApiService,
        private val completedDtoToDomainMapper: CompletedChallengesDtoToDomainMapper,
        private val authoredDtoToDomainMapper: AuthoredChallengesDtoToDomainMapper)
    : DataSource.Factory<Int, ChallengeDomain>() {

    override fun create(): DataSource<Int, ChallengeDomain> =
            when (flow) {
                COMPLETED_CHALLENGES -> CompletedChallengesDataSource(
                        userName, challengesApiService, completedDtoToDomainMapper)
                AUTHORED_CHALLENGES -> AuthoredChallengesDataSource(
                        userName, challengesApiService, authoredDtoToDomainMapper)
            }
}