package com.github.fatihsokmen.codewars.challenges

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.github.fatihsokmen.codewars.App
import com.github.fatihsokmen.codewars.R
import javax.inject.Inject
import javax.inject.Named


class ChallengesFragment : Fragment() {

    @BindView(R.id.challenges)
    lateinit var challengesResultView: RecyclerView

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var adapter: ChallengesResultsAdapter


    private lateinit var viewModel: ChallengesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_challenges, container, false)
        ButterKnife.bind(this, view)

        val userName = activity!!.intent!!.getStringExtra("userName")
        val flow = arguments?.getSerializable("flow") as Flow

        createChallengesComponent(this, userName, flow).inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChallengesViewModel::class.java)

        bindViewModel()

        challengesResultView.adapter = adapter
        return view
    }

    private fun bindViewModel() {
        viewModel.challenges.observe(this, Observer { challenges ->
            challenges?.let {
                adapter.submitList(challenges)
            }
        })
    }

    companion object {
        private fun createChallengesComponent(fragment: ChallengesFragment, userName: String, flow: Flow)
                : ChallengesFragmentComponent {
            val baseComponent = (fragment.activity?.application as App).baseComponent
            return DaggerChallengesFragmentComponent
                    .builder()
                    .baseComponent(baseComponent)
                    .flow(flow)
                    .userName(userName)
                    .build()
        }
    }
}
