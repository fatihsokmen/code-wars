package com.github.fatihsokmen.codewars.search.viewholder.recent

import com.github.fatihsokmen.codewars.dependency.scope.ViewHolderScope
import com.github.fatihsokmen.codewars.search.viewholder.BaseViewHolder
import com.github.fatihsokmen.codewars.search.viewholder.ViewHolderLayoutModule
import dagger.Binds
import dagger.Module

@Module(includes = [ViewHolderLayoutModule::class])
abstract class RecentViewHolderModule {

    @Binds
    @ViewHolderScope
    abstract fun bindViewHolder(impl: RecentViewHolderView): BaseViewHolder

}