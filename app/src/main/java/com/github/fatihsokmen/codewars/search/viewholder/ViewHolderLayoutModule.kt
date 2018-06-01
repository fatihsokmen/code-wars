package com.github.fatihsokmen.codewars.search.viewholder

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.fatihsokmen.codewars.dependency.scope.ViewHolderScope
import dagger.Module
import dagger.Provides

@Module
class ViewHolderLayoutModule(@param:LayoutRes @field:LayoutRes private val layoutId: Int) {

    @Provides
    @ViewHolderScope
    fun provideItemView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }
}