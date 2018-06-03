package com.github.fatihsokmen.codewars.search.viewholder.search


import com.github.fatihsokmen.codewars.dependency.BaseComponent
import com.github.fatihsokmen.codewars.dependency.scope.ViewHolderScope
import com.github.fatihsokmen.codewars.search.viewholder.BaseViewHolderFactory
import dagger.Component

@Component(dependencies = [BaseComponent::class], modules = [SearchViewHolderModule::class])
@ViewHolderScope
interface SearchViewHolderFactory : BaseViewHolderFactory {

    @Component.Builder
    interface Builder : BaseViewHolderFactory.Builder
}