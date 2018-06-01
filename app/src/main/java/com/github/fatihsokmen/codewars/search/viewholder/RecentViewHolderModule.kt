package com.github.fatihsokmen.codewars.search.viewholder

import com.github.fatihsokmen.codewars.dependency.scope.ViewHolderScope
import dagger.Binds
import dagger.Module

@Module(includes = [ViewHolderLayoutModule::class])
abstract class RecentViewHolderModule {

    @Binds
    @ViewHolderScope
    abstract fun bindViewHolder(impl: RecentViewHolderView): BaseViewHolder

}