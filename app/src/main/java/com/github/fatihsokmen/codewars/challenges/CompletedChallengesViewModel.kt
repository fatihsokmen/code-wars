package com.github.fatihsokmen.codewars.challenges

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.fatihsokmen.codewars.data.CompletedChallengeDomain
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CompletedChallengesViewModel constructor(
        challengesDataSourceFactory: ChallengesDataSourceFactory) : ViewModel() {

    private val subscriptions = CompositeDisposable()
    private val challenges: LiveData<PagedList<CompletedChallengeDomain>>

    init {
        val config = PagedList.Config.Builder()
                .setPageSize(1)
                .setInitialLoadSizeHint(0)
                .setEnablePlaceholders(true)
                .build()
        challenges = LivePagedListBuilder<Int, CompletedChallengeDomain>(
                challengesDataSourceFactory, config).build()
    }

    fun challenges(): LiveData<PagedList<CompletedChallengeDomain>> = challenges


    override fun onCleared() {
        subscriptions.clear()
    }

    class Factory @Inject constructor(
            private val challengesDataSourceFactory: ChallengesDataSourceFactory)
        : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CompletedChallengesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CompletedChallengesViewModel(challengesDataSourceFactory) as T
            }
            throw IllegalArgumentException("Unsupported ViewModel class")
        }
    }

    companion object {
        private const val TAG = "ChallengesViewModel"
    }
}
