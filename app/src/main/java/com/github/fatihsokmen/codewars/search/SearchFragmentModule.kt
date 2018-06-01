package com.github.fatihsokmen.codewars.search

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.datasource.remote.UserSearchService
import com.github.fatihsokmen.codewars.dependency.BaseComponent
import com.github.fatihsokmen.codewars.dependency.scope.FragmentViewScope
import com.github.fatihsokmen.codewars.search.adapter.SearchResultsAdapter
import com.github.fatihsokmen.codewars.search.viewholder.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
abstract class SearchFragmentModule {

    @Binds
    @FragmentViewScope
    abstract fun bindViewModelFactory(impl: SearchViewModel.Factory): ViewModelProvider.Factory

    @Module
    companion object {

        @JvmStatic
        @Provides
        @FragmentViewScope
        fun provideViewModel(fragment: SearchFragment,
                             viewModelFactory: ViewModelProvider.Factory): SearchViewModel {
            return ViewModelProviders.of(fragment, viewModelFactory).get(SearchViewModel::class.java)
        }

        @JvmStatic
        @Provides
        @FragmentViewScope
        fun provideUserSearchService(retrofit: Retrofit): UserSearchService {
            return retrofit.create(UserSearchService::class.java)
        }

        @JvmStatic
        @IntoMap
        @IntKey(SearchResultsAdapter.TYPE_SEARCH_ITEM)
        @FragmentViewScope
        @Provides
        fun provideUserViewHolderFactory(baseComponent: BaseComponent): BaseViewHolderFactory.Builder {
            return DaggerSearchViewHolderFactory
                    .builder()
                    .baseComponent(baseComponent)
                    .layoutModule(ViewHolderLayoutModule(R.layout.view_search_item))
        }

        @JvmStatic
        @IntoMap
        @IntKey(SearchResultsAdapter.TYPE_RECENT_ITEM)
        @FragmentViewScope
        @Provides
        fun provideRecentViewHolderFactory(baseComponent: BaseComponent): BaseViewHolderFactory.Builder {
            return DaggerRecentViewHolderFactory
                    .builder()
                    .baseComponent(baseComponent)
                    .layoutModule(ViewHolderLayoutModule(R.layout.view_recent_item))
        }
    }
}
