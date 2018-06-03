package com.github.fatihsokmen.codewars.search.viewholder.recent


import com.github.fatihsokmen.codewars.dependency.BaseComponent
import com.github.fatihsokmen.codewars.dependency.scope.ViewHolderScope
import com.github.fatihsokmen.codewars.search.viewholder.BaseViewHolderFactory
import dagger.Component

@Component(dependencies = [BaseComponent::class], modules = [RecentViewHolderModule::class])
@ViewHolderScope
interface RecentViewHolderFactory : BaseViewHolderFactory {

    @Component.Builder
    interface Builder : BaseViewHolderFactory.Builder
}