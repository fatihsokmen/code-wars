package com.github.fatihsokmen.codewars.challenges

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.data.ChallengeDomain


class ChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.challenge_name)
    lateinit var name: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bind(challenge: ChallengeDomain?) {
        name.text = challenge?.name
    }
}