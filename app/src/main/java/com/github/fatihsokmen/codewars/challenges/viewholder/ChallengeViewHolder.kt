package com.github.fatihsokmen.codewars.challenges.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.data.CompletedChallengeDomain


class ChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.challenge_name)
    lateinit var name: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bind(challenge: CompletedChallengeDomain?) {
        name.text = challenge?.name
    }
}