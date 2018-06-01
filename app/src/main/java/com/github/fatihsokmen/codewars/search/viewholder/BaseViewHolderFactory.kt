package com.github.fatihsokmen.codewars.search.viewholder


import android.view.ViewGroup
import com.github.fatihsokmen.codewars.dependency.BaseComponent
import dagger.BindsInstance

interface BaseViewHolderFactory {

    fun createViewHolder(): BaseViewHolder

    interface Builder {

        @BindsInstance
        fun parentView(parentView: ViewGroup): Builder

        fun baseComponent(baseComponent: BaseComponent): Builder

        fun layoutModule(layoutModule: ViewHolderLayoutModule): Builder

        fun build(): BaseViewHolderFactory
    }
}