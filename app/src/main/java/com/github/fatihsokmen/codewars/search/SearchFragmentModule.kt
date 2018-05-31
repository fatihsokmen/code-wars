package com.github.fatihsokmen.codewars.search

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders

import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.datasource.remote.UserSearchService
import com.github.fatihsokmen.codewars.dependency.scope.FragmentViewScope
import com.github.fatihsokmen.codewars.search.viewholder.DaggerSearchResultViewHolderFactory
import com.github.fatihsokmen.codewars.search.viewholder.SearchResultViewHolderFactory
import com.github.fatihsokmen.codewars.search.viewholder.SearchResultViewHolderModule

import dagger.Binds
import dagger.Module
import dagger.Provides
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
        fun provideViewHolderFactory(): SearchResultViewHolderFactory.Builder {
            return DaggerSearchResultViewHolderFactory
                    .builder()
                    //.viewHolderInteractions(null)
                    .layoutModule(SearchResultViewHolderModule.SearchResultViewHolderLayoutModule(
                            R.layout.view_search_result_item))
        }

        @JvmStatic
        @Provides
        @FragmentViewScope
        fun provideUserSearchService(retrofit: Retrofit): UserSearchService {
            return retrofit.create(UserSearchService::class.java)
        }
    }


}
