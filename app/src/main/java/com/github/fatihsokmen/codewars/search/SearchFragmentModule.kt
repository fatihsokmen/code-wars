package com.github.fatihsokmen.codewars.search

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.data.remote.search.UserSearchService
import com.github.fatihsokmen.codewars.dependency.BaseComponent
import com.github.fatihsokmen.codewars.dependency.scope.FragmentViewScope
import com.github.fatihsokmen.codewars.search.viewholder.BaseViewHolderFactory
import com.github.fatihsokmen.codewars.search.viewholder.ViewHolderLayoutModule
import com.github.fatihsokmen.codewars.search.viewholder.recent.DaggerRecentViewHolderFactory
import com.github.fatihsokmen.codewars.search.viewholder.search.DaggerSearchViewHolderFactory
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
        fun provideUserSearchService(retrofit: Retrofit): UserSearchService {
            return retrofit.create(UserSearchService::class.java)
        }

        @JvmStatic
        @IntoMap
        @IntKey(UserModel.HISTORY)
        @FragmentViewScope
        @Provides
        fun provideUserViewHolderFactory(baseComponent: BaseComponent): BaseViewHolderFactory.Builder {
            return DaggerSearchViewHolderFactory
                    .builder()
                    .baseComponent(baseComponent)
                    .layoutModule(ViewHolderLayoutModule(R.layout.view_history_item))
        }

        @JvmStatic
        @IntoMap
        @IntKey(UserModel.RECENT)
        @FragmentViewScope
        @Provides
        fun provideRecentViewHolderFactory(baseComponent: BaseComponent): BaseViewHolderFactory.Builder {
            return DaggerRecentViewHolderFactory
                    .builder()
                    .baseComponent(baseComponent)
                    .layoutModule(ViewHolderLayoutModule(R.layout.view_recent_item))
        }

        @JvmStatic
        @IntoMap
        @IntKey(UserModel.SEARCH)
        @FragmentViewScope
        @Provides
        fun provideSearchViewHolderFactory(baseComponent: BaseComponent): BaseViewHolderFactory.Builder {
            return DaggerSearchViewHolderFactory
                    .builder()
                    .baseComponent(baseComponent)
                    .layoutModule(ViewHolderLayoutModule(R.layout.view_search_item))
        }

        @JvmStatic
        @Provides
        @FragmentViewScope
        fun provideItemDecoration(context: Context): RecyclerView.ItemDecoration {
            val itemDecoration = DividerItemDecoration(context,
                    DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(
                    context, android.R.drawable.divider_horizontal_bright)!!)
            return itemDecoration
        }
    }
}
