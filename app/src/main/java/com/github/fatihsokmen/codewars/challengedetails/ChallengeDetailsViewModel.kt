package com.github.fatihsokmen.codewars.challengedetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.fatihsokmen.codewars.data.ChallengeDetailsDomain
import com.github.fatihsokmen.codewars.dependency.scheduler.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ChallengeDetailsViewModel constructor(
        private val challengeId: String,
        private val challengeDetailsRepository: ChallengeDetailsRepository,
        private val scheduler: Scheduler
) : ViewModel() {

    private val subscriptions: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    var details: MutableLiveData<ChallengeDetailsResource<ChallengeDetailsDomain>>? = null

    fun details(): LiveData<ChallengeDetailsResource<ChallengeDetailsDomain>> {
        if (details == null) {
            details = MutableLiveData()
            getChallengeDetails(challengeId)
        }
        return details as LiveData<ChallengeDetailsResource<ChallengeDetailsDomain>>
    }

    private fun getChallengeDetails(challengeId: String) {
        details?.value = ChallengeDetailsResource.loading()

        subscriptions.add(challengeDetailsRepository.getChallengeDetails(challengeId)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.main())
                .subscribe({
                    details?.value = ChallengeDetailsResource.success(it)
                }, {
                    details?.value = ChallengeDetailsResource.error(it.message)
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