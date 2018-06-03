package com.github.fatihsokmen.codewars.challengedetails

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.fatihsokmen.codewars.data.ChallengeDetailsDomain
import com.github.fatihsokmen.codewars.dependency.scheduler.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ChallengeDetailsViewModel constructor(
        challengeId: String,
        private val challengeDetailsRepository: ChallengeDetailsRepository,
        private val scheduler: Scheduler
) : ViewModel() {

    private val subscriptions: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    var details = MutableLiveData<ChallengeDetailsDomain>()

    init {
        getChallengeDetails(challengeId)
    }

    private fun getChallengeDetails(challengeId: String) {
        subscriptions.add(challengeDetailsRepository.getChallengeDetails(challengeId)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.main())
                .subscribe({
                    details.value = it
                },{
                    print("Error")
                }))
    }

    override fun onCleared() {
        subscriptions.clear()
    }

    class Factory @Inject constructor(
            private val challengeId: String,
            private val challengeDetailsRepository: ChallengeDetailsRepository,
            private val scheduler: Scheduler)
        : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(ChallengeDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ChallengeDetailsViewModel(challengeId, challengeDetailsRepository, scheduler) as T
            }
            throw IllegalArgumentException("Unsupported ViewModel class")
        }
    }
}