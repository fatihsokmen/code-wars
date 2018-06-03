package com.github.fatihsokmen.codewars.challengedetails

import android.arch.lifecycle.ViewModelProvider
import com.github.fatihsokmen.codewars.data.remote.challenges.ChallengeApiService
import com.github.fatihsokmen.codewars.dependency.scope.FragmentViewScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class ChallengeDetailsFragmentModule {

    @Binds
    @FragmentViewScope
    abstract fun bindViewModelFactory(impl: ChallengeDetailsViewModel.Factory): ViewModelProvider.Factory

    @Module
    companion object {

        @JvmStatic
        @Provides
        @FragmentViewScope
        fun provideChallengesService(retrofit: Retrofit): ChallengeApiService {
            return retrofit.create(ChallengeApiService::class.java)
        }
    }
}
