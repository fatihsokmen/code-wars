package com.github.fatihsokmen.codewars.challengedetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.github.fatihsokmen.codewars.App
import com.github.fatihsokmen.codewars.R
import javax.inject.Inject


class ChallengeDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ChallengeDetailsViewModel

    private var viewBindings = ChallengeDetailsViewBindings()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_challenge_details, container, false)
        viewBindings.init(view)

        val challenge = activity!!.intent!!.getStringExtra("challengeId")

        createChallengeDetailsComponent(this, challenge).inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChallengeDetailsViewModel::class.java)

        bindViewModel()

        return view
    }

    private fun bindViewModel() {
        viewModel.details.observe(this, Observer { challenges ->
            challenges?.let { details ->
                viewBindings.bind(details)
            }
        })
    }

    companion object {
        private fun createChallengeDetailsComponent(fragment: ChallengeDetailsFragment, challengeId: String)
                : ChallengeDetailsFragmentComponent {
            val baseComponent = (fragment.activity?.application as App).baseComponent
            return DaggerChallengeDetailsFragmentComponent
                    .builder()
                    .baseComponent(baseComponent)
                    .challengeId(challengeId)
                    .build()
        }
    }
}