package com.github.fatihsokmen.codewars.challenges.datasource

import android.arch.paging.PageKeyedDataSource
import com.github.fatihsokmen.codewars.data.ChallengeDomain
import com.github.fatihsokmen.codewars.data.mapper.challenges.AuthoredChallengesDtoToDomainMapper
import com.github.fatihsokmen.codewars.data.remote.challenges.ChallengeApiService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AuthoredChallengesDataSource @Inject constructor(
        private val userName: String,
        private val challengesService: ChallengeApiService,
        private val challengesDtoToDomainMapper: AuthoredChallengesDtoToDomainMapper)
    : PageKeyedDataSource<Int, ChallengeDomain>() {

    private val subscriptions: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ChallengeDomain>) {
        subscriptions.add(
                challengesService.getAuthoredChallenges(userName)
                        .subscribe({ challenges ->
                            challenges?.let {
                                callback.onResult(challengesDtoToDomainMapper.apply(it), null, null)
                            }
                        }, {
                            print("Error")
                        })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ChallengeDomain>) {
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ChallengeDomain>) {
    }
}
