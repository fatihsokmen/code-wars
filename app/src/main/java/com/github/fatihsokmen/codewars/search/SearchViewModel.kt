package com.github.fatihsokmen.codewars.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.github.fatihsokmen.codewars.datasource.UserDomain
import com.github.fatihsokmen.codewars.dependency.scheduler.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchViewModel constructor(private val searchRepository: SearchRepository,
                                  private val scheduler: Scheduler) : ViewModel() {

    private val subscriptions = CompositeDisposable()
    private val recentUsers = MutableLiveData<List<UserDomain>>()
    private val searchedUser = MutableLiveData<UserDomain>()

    init {
        getRecent()
    }

    fun recentUsers(): LiveData<List<UserDomain>> = recentUsers

    fun searchedUser(): LiveData<UserDomain> = searchedUser

    fun getRecent() {
        subscriptions.add(searchRepository.getRecent()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.main())
                .subscribe({ users ->
                    recentUsers.value = users
                }, { throwable ->
                    Log.d(TAG, throwable.message)
                }, {
                    Log.d(TAG, "Completed")
                }))
    }

    fun searchUser(query: String) {
        subscriptions.add(searchRepository.searchUser(query)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.main())
                .subscribe({ user ->
                    searchedUser.value = user
                }, { throwable ->
                    Log.d(TAG, throwable.message)
                }))
    }


    override fun onCleared() {
        subscriptions.clear()
    }

    class Factory @Inject constructor(
            private val searchRepository: SearchRepository,
            private val scheduler: Scheduler)
        : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                return SearchViewModel(searchRepository, scheduler) as T
            }
            throw IllegalArgumentException("Unsupported ViewModel class")
        }
    }

    companion object {
        private const val TAG = "SearchViewModel"
    }
}
