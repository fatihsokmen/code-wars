package com.github.fatihsokmen.codewars.challenges

import android.arch.lifecycle.ViewModelProvider
import android.arch.paging.DataSource
import com.github.fatihsokmen.codewars.data.CompletedChallengeDomain
import com.github.fatihsokmen.codewars.data.remote.challenges.ChallengesApiService
import com.github.fatihsokmen.codewars.dependency.scope.FragmentViewScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class ChallengesFragmentModule {

    @Binds
    @FragmentViewScope
    abstract fun bindViewModelFactory(impl: CompletedChallengesViewModel.Factory): ViewModelProvider.Factory

    @Binds
    @FragmentViewScope
    abstract fun bindPagingDataSourceFactory(impl: ChallengesDataSourceFactory):
            DataSource.Factory<@JvmSuppressWildcards Int, @JvmSuppressWildcards CompletedChallengeDomain>

    @Module
    companion object {

        @JvmStatic
        @Provides
        @FragmentViewScope
        fun provideChallengesService(retrofit: Retrofit): ChallengesApiService {
            return retrofit.create(ChallengesApiService::class.java)
        }
    }
}