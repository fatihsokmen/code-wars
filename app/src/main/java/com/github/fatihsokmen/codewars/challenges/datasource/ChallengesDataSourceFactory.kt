package com.github.fatihsokmen.codewars.challenges.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.fatihsokmen.codewars.challenges.ChallengeProgressingResource
import com.github.fatihsokmen.codewars.challenges.Flow
import com.github.fatihsokmen.codewars.challenges.Flow.AUTHORED_CHALLENGES
import com.github.fatihsokmen.codewars.challenges.Flow.COMPLETED_CHALLENGES
import com.github.fatihsokmen.codewars.data.ChallengeDomain
import com.github.fatihsokmen.codewars.data.mapper.challenges.AuthoredChallengesDtoToDomainMapper
import com.github.fatihsokmen.codewars.data.mapper.challenges.CompletedChallengesDtoToDomainMapper
import com.github.fatihsokmen.codewars.data.remote.challenges.ChallengeApiService
import javax.inject.Inject

class ChallengesDataSourceFactory @Inject constructor(
        private val userName: String,
        private val flow: Flow,
        private val challengesApiService: ChallengeApiService,
        private val completedDtoToDomainMapper: CompletedChallengesDtoToDomainMapper,
        private val authoredDtoToDomainMapper: AuthoredChallengesDtoToDomainMapper,
        private val progressingData: MutableLiveData<ChallengeProgressingResource>)
    : DataSource.Factory<Int, ChallengeDomain>() {

    override fun create(): DataSource<Int, ChallengeDomain> =
            when (flow) {
                COMPLETED_CHALLENGES -> CompletedChallengesDataSource(
                        userName, challengesApiService, completedDtoToDomainMapper, progressingData)
                AUTHORED_CHALLENGES -> AuthoredChallengesDataSource(
                        userName, challengesApiService, authoredDtoToDomainMapper, progressingData)
            }
}