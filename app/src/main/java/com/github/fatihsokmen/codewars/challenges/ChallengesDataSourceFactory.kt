package com.github.fatihsokmen.codewars.challenges

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.fatihsokmen.codewars.data.CompletedChallengeDomain
import com.github.fatihsokmen.codewars.data.mapper.challenges.CompletedChallengesDtoToDomainMapper
import com.github.fatihsokmen.codewars.data.remote.challenges.ChallengesApiService
import javax.inject.Inject

class ChallengesDataSourceFactory @Inject constructor(
        private val userName: String,
        private val challengesApiService: ChallengesApiService,
        private val challengesDtoToDomainMapper: CompletedChallengesDtoToDomainMapper)
    : DataSource.Factory<Int, CompletedChallengeDomain>() {

    val dataSourceLiveData = MutableLiveData<ChallengesDataSource>()

    override fun create(): DataSource<Int, CompletedChallengeDomain> {
        val dataSource = ChallengesDataSource(userName, challengesApiService, challengesDtoToDomainMapper)
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}