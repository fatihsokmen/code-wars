package com.github.fatihsokmen.codewars.challenges

import android.arch.lifecycle.MutableLiveData
import com.github.fatihsokmen.codewars.dependency.BaseComponent
import com.github.fatihsokmen.codewars.dependency.scope.FragmentViewScope
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [BaseComponent::class], modules = [ChallengesFragmentModule::class])
@FragmentViewScope
interface ChallengesFragmentComponent {

    fun inject(fragment: ChallengesFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun userName(userName: String): Builder

        @BindsInstance
        fun flow(flow: Flow): Builder

        @BindsInstance
        fun progressingData(progressingData: MutableLiveData<ChallengeProgressingResource>): Builder

        fun baseComponent(baseComponent: BaseComponent): Builder

        fun build(): ChallengesFragmentComponent
    }
}