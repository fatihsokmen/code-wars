package com.github.fatihsokmen.codewars.search.viewholder


import android.view.ViewGroup

import com.github.fatihsokmen.codewars.dependency.scope.ViewHolderScope

import dagger.BindsInstance
import dagger.Component

@Component(modules = [SearchResultViewHolderModule::class])
@ViewHolderScope
interface SearchResultViewHolderFactory {

    fun createViewHolder(): SearchResultViewHolder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun parentView(parentView: ViewGroup): Builder

        fun layoutModule(layoutModule: SearchResultViewHolderModule.SearchResultViewHolderLayoutModule): Builder

        fun build(): SearchResultViewHolderFactory

    }
}