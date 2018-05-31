package com.github.fatihsokmen.codewars.search

import com.github.fatihsokmen.codewars.dependency.BaseComponent
import com.github.fatihsokmen.codewars.dependency.scope.FragmentViewScope

import dagger.Component

@Component(dependencies = [BaseComponent::class], modules = [SearchFragmentModule::class])
@FragmentViewScope
interface SearchFragmentComponent {

    fun inject(fragment: SearchFragment)

    @Component.Builder
    interface Builder {

        fun baseComponent(baseComponent: BaseComponent): Builder

        fun build(): SearchFragmentComponent
    }
}