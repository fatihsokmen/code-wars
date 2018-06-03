package com.github.fatihsokmen.codewars.challenges

import android.arch.paging.PageKeyedDataSource
import com.github.fatihsokmen.codewars.data.CompletedChallengeDomain
import com.github.fatihsokmen.codewars.data.mapper.challenges.CompletedChallengesDtoToDomainMapper
import com.github.fatihsokmen.codewars.data.remote.challenges.ChallengesApiService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ChallengesDataSource @Inject constructor(
        private val userName: String,
        private val challengesService: ChallengesApiService,
        private val challengesDtoToDomainMapper: CompletedChallengesDtoToDomainMapper)
    : PageKeyedDataSource<Int, CompletedChallengeDomain>() {

    private val subscriptions: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, CompletedChallengeDomain>) {
        subscriptions.add(
                challengesService.getCompletedChallenges(userName, params.requestedLoadSize)
                        .subscribe({ challenges ->
                            challenges?.let {
                                callback.onResult(challengesDtoToDomainMapper.apply(it), null, params.requestedLoadSize + 1)
                            }
                        }, {
                            print("Error")
                        })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CompletedChallengeDomain>) {
        subscriptions.add(
                challengesService.getCompletedChallenges(userName, params.key)
                        .subscribe({ challenges ->
                            challenges?.let {
                                callback.onResult(challengesDtoToDomainMapper.apply(it),
                                        params.key + 1)
                            }
                        }, {
                            print("Error")
                        })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CompletedChallengeDomain>) {
        subscriptions.add(
                challengesService.getCompletedChallenges(userName, params.key)
                        .subscribe({ challenges ->
                            challenges?.let {
                                callback.onResult(challengesDtoToDomainMapper.apply(it),
                                        params.key - 1)
                            }
                        }, {
                            print("Error")
                        })
        )
    }
}
