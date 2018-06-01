package com.github.fatihsokmen.codewars.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.github.fatihsokmen.codewars.App
import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.search.adapter.SearchResultsAdapter
import javax.inject.Inject


class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    @BindView(R.id.search)
    lateinit var searchView: SearchView
    @BindView(R.id.words)
    lateinit var searchResultView: RecyclerView

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var adapter: SearchResultsAdapter

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        ButterKnife.bind(this, view)

        createSearchComponent(this).inject(this)

        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        searchResultView.adapter = adapter
        searchView.setOnQueryTextListener(this)

        bindViewModel()

        return view
    }

    private fun bindViewModel() {
        searchViewModel.recentUsers().observe(this, Observer { users ->
            users?.let { adapter.setData(users) }
        })
    }


    override fun onQueryTextChange(query: String): Boolean {
        searchViewModel.searchUser(query)
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchViewModel.searchUser(query)
        return true
    }

    companion object {
        fun createSearchComponent(fragment: SearchFragment): SearchFragmentComponent {
            val baseComponent = (fragment.activity?.application as App).baseComponent
            return DaggerSearchFragmentComponent
                    .builder()
                    .baseComponent(baseComponent)
                    .build()
        }
    }
}
