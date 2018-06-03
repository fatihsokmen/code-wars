package com.github.fatihsokmen.codewars.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.github.fatihsokmen.codewars.App
import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.search.adapter.SearchResultsAdapter
import java.util.*
import javax.inject.Inject


class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    @BindView(R.id.search)
    lateinit var searchView: SearchView
    @BindView(R.id.users)
    lateinit var searchResultView: RecyclerView
    @BindView(R.id.progress)
    lateinit var progress: ProgressBar

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var adapter: SearchResultsAdapter
    @Inject
    lateinit var itemDecorator: RecyclerView.ItemDecoration

    private lateinit var viewModel: SearchViewModel

    private val recentUsersObserver = Observer<List<UserModel>> { users ->
        users?.let { models ->
            adapter.setData(models)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        ButterKnife.bind(this, view)

        createSearchComponent(this).inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        searchResultView.addItemDecoration(itemDecorator)
        searchResultView.adapter = adapter
        searchView.setOnQueryTextListener(this)

        bindViewModel()

        return view
    }

    private fun bindViewModel() {
        viewModel.recentUsers().observe(this, recentUsersObserver)

        viewModel.searchedUser().observe(this, Observer { resource ->
            when (resource?.status) {
                Status.LOADING -> {
                    progress.visibility = View.VISIBLE
                    searchResultView.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    progress.visibility = View.GONE
                    searchResultView.visibility = View.VISIBLE
                    viewModel.recentUsers().removeObserver(recentUsersObserver)
                    resource.data?.let {
                        adapter.setData(Collections.singletonList(it))
                    }
                }
                Status.ERROR -> {
                    progress.visibility = View.GONE
                    searchResultView.visibility = View.VISIBLE
                    viewModel.recentUsers().removeObserver(recentUsersObserver)
                    adapter.setData(Collections.emptyList())
                    Snackbar.make(searchResultView, "Not found", Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }


    override fun onQueryTextChange(query: String): Boolean {
        if (query.trim().isEmpty()) {
            viewModel.recentUsers().removeObserver(recentUsersObserver)
            viewModel.recentUsers().observe(this, recentUsersObserver)
        }
        return false
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        if (!query.trim().isEmpty()) {
            viewModel.searchUser(query)
            return true
        }
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
