package com.github.fatihsokmen.codewars.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.github.fatihsokmen.codewars.R.id.users
import com.github.fatihsokmen.codewars.dependency.scheduler.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchViewModel constructor(private val searchRepository: SearchRepository,
                                  private val userHistoryDomainToModelMapper: UserHistoryDomainToModelMapper,
                                  private val userDomainToModelMapper: UserDomainToModelMapper,
                                  private val scheduler: Scheduler) : ViewModel() {

    private val subscriptions = CompositeDisposable()
    private val recentUsers = MutableLiveData<List<UserModel>>()
    private val searchedUser = MutableLiveData<SearchResource<UserModel>>()

    init {
        getRecent()
    }

    fun recentUsers(): LiveData<List<UserModel>> = recentUsers

    fun searchedUser(): LiveData<SearchResource<UserModel>> = searchedUser

    private fun getRecent() {
        subscriptions.add(searchRepository.getRecent()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.main())
                .map { userDomains ->
                    userHistoryDomainToModelMapper.apply(userDomains)
                }
                .subscribe({ users ->
                    recentUsers.value = users
                }, { throwable ->
                    Log.d(TAG, throwable.message)
                }, {
                    Log.d(TAG, "Completed")
                }))
    }

    fun searchUser(query: String) {
        searchedUser.value = SearchResource.loading()
        subscriptions.add(searchRepository.searchUser(query)
                .map { userDomain ->
                    userDomainToModelMapper.apply(userDomain)
                }
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.main())
                .subscribe({ user ->
                    searchedUser.value = SearchResource.success(user)
                }, { throwable ->
                    searchedUser.value = SearchResource.error(throwable.message)
                }))
    }


    override fun onCleared() {
        subscriptions.clear()
    }

    class Factory @Inject constructor(
            private val searchRepository: SearchRepository,
            private val userHistoryDomainToModelMapper: UserHistoryDomainToModelMapper,
            private val userDomainToModelMapper: UserDomainToModelMapper,
            private val scheduler: Scheduler
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchViewModel(searchRepository, userHistoryDomainToModelMapper,
                        userDomainToModelMapper, scheduler) as T
            }
            throw IllegalArgumentException("Unsupported ViewModel class")
        }
    }

    companion object {
        private const val TAG = "SearchViewModel"
    }
}
