package com.github.fatihsokmen.codewars.challenges

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.github.fatihsokmen.codewars.App
import com.github.fatihsokmen.codewars.R
import kotlinx.android.synthetic.main.fragment_challenge_details.*
import javax.inject.Inject
import javax.inject.Named


class ChallengesFragment : Fragment() {

    @BindView(R.id.challenges)
    lateinit var challengesResultView: RecyclerView
    @BindView(R.id.progress)
    lateinit var progress: ProgressBar

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var adapter: ChallengesResultsAdapter


    private lateinit var viewModel: ChallengesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_challenges, container, false)
        ButterKnife.bind(this, view)

        val userName = activity?.intent?.getStringExtra("userName")
                ?: throw IllegalArgumentException("User name does not exist in bundle")
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
        viewModel.progressingData().observe(this, Observer { resource ->
            resource?.let {
                when (resource.status) {
                    ChallengeProgressingResource.Status.LOADING -> progress.visibility = View.VISIBLE
                    ChallengeProgressingResource.Status.SUCCESS -> progress.visibility = View.GONE
                    ChallengeProgressingResource.Status.ERROR -> {
                        progress.visibility = View.GONE
                    }
                }
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
                    .progressingData(MutableLiveData())
                    .build()
        }
    }
}
