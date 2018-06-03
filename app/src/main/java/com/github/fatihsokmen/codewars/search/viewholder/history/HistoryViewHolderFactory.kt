package com.github.fatihsokmen.codewars.search.viewholder.history


import com.github.fatihsokmen.codewars.dependency.BaseComponent
import com.github.fatihsokmen.codewars.dependency.scope.ViewHolderScope
import com.github.fatihsokmen.codewars.search.viewholder.BaseViewHolderFactory
import dagger.Component

@Component(dependencies = [BaseComponent::class], modules = [HistoryViewHolderModule::class])
@ViewHolderScope
interface HistoryViewHolderFactory : BaseViewHolderFactory {

    @Component.Builder
    interface Builder : BaseViewHolderFactory.Builder
}