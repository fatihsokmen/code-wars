package com.github.fatihsokmen.codewars.search.viewholder

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.github.fatihsokmen.codewars.dependency.scope.ViewHolderScope

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [SearchResultViewHolderModule.SearchResultViewHolderLayoutModule::class])
abstract class SearchResultViewHolderModule {

    @Binds
    @ViewHolderScope
    abstract fun bindViewHolder(impl: SearchResultViewHolderView): SearchResultViewHolder

    @Module
    class SearchResultViewHolderLayoutModule(@param:LayoutRes @field:LayoutRes private val layoutId: Int) {

        @Provides
        @ViewHolderScope
        fun provideViewHolderLayout(parent: ViewGroup): View {
            return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        }

    }
}