package com.github.fatihsokmen.codewars.challenges

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.challengedetails.ChallengeDetailsActivity
import com.github.fatihsokmen.codewars.data.ChallengeDomain


class ChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.challenge_name)
    lateinit var name: TextView

    private lateinit var challenge: ChallengeDomain

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bind(challenge: ChallengeDomain?) {
        challenge?.let {
            this.challenge = it
            name.text = it.name
        }
    }

    @OnClick(R.id.challenge_name)
    fun onChallengeClicked() {
        val context = itemView.context
        val intent = ChallengeDetailsActivity.getIntent(context = itemView.context, challengeId = challenge.id)
        context.startActivity(intent)
    }
}