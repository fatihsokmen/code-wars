package com.github.fatihsokmen.codewars.challengedetails

import com.github.fatihsokmen.codewars.challenges.ChallengesFragment
import com.github.fatihsokmen.codewars.challenges.ChallengesFragmentModule
import com.github.fatihsokmen.codewars.dependency.BaseComponent
import com.github.fatihsokmen.codewars.dependency.scope.FragmentViewScope

import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [BaseComponent::class], modules = [ChallengeDetailsFragmentModule::class])
@FragmentViewScope
interface ChallengeDetailsFragmentComponent {

    fun inject(fragment: ChallengeDetailsFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun challengeId(challengeId: String): Builder

        fun baseComponent(baseComponent: BaseComponent): Builder

        fun build(): ChallengeDetailsFragmentComponent
    }
}