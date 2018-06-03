package com.github.fatihsokmen.codewars.challenges.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.github.fatihsokmen.codewars.challenges.ChallengeProgressingResource
import com.github.fatihsokmen.codewars.data.ChallengeDomain
import com.github.fatihsokmen.codewars.data.mapper.challenges.CompletedChallengesDtoToDomainMapper
import com.github.fatihsokmen.codewars.data.remote.challenges.ChallengeApiService
import io.reactivex.disposables.CompositeDisposable

class CompletedChallengesDataSource constructor(
        private val userName: String,
        private val challengesService: ChallengeApiService,
        private val challengesDtoToDomainMapper: CompletedChallengesDtoToDomainMapper,
        private val progressingData: MutableLiveData<ChallengeProgressingResource>)
    : PageKeyedDataSource<Int, ChallengeDomain>() {

    private val subscriptions: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ChallengeDomain>) {
        progressingData.postValue(ChallengeProgressingResource.loading())
        subscriptions.add(
                challengesService.getCompletedChallenges(userName, params.requestedLoadSize)
                        .subscribe({ challenges ->
                            challenges?.let {
                                callback.onResult(challengesDtoToDomainMapper.apply(it), null, params.requestedLoadSize + 1)
                                progressingData.postValue(ChallengeProgressingResource.success())
                            }
                        }, {
                            progressingData.postValue(ChallengeProgressingResource.error(it.message))
                        })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ChallengeDomain>) {
        progressingData.postValue(ChallengeProgressingResource.loading())
        subscriptions.add(
                challengesService.getCompletedChallenges(userName, params.key)
                        .subscribe({ challenges ->
                            challenges?.let {
                                callback.onResult(challengesDtoToDomainMapper.apply(it),
                                        params.key + 1)
                                progressingData.postValue(ChallengeProgressingResource.success())
                            }
                        }, {
                            progressingData.postValue(ChallengeProgressingResource.error(it.message))
                        })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ChallengeDomain>) {
        progressingData.postValue(ChallengeProgressingResource.loading())
        subscriptions.add(
                challengesService.getCompletedChallenges(userName, params.key)
                        .subscribe({ challenges ->
                            challenges?.let {
                                callback.onResult(challengesDtoToDomainMapper.apply(it),
                                        params.key - 1)
                                progressingData.postValue(ChallengeProgressingResource.success())
                            }
                        }, {
                            progressingData.postValue(ChallengeProgressingResource.error(it.message))
                        })
        )
    }
}
