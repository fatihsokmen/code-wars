package com.github.fatihsokmen.codewars.challenges


import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.data.ChallengeDomain
import javax.inject.Inject


class ChallengesResultsAdapter @Inject constructor()
    : PagedListAdapter<ChallengeDomain, ChallengeViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_challenge_item, parent, false)
        return ChallengeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ChallengeDomain>() {
            override fun areItemsTheSame(
                    oldItem: ChallengeDomain?,
                    newItem: ChallengeDomain?): Boolean {
                return oldItem?.id == newItem?.id
            }

            override fun areContentsTheSame(
                    oldItem: ChallengeDomain?,
                    newItem: ChallengeDomain?): Boolean {
                return oldItem?.id == newItem?.id
            }
        }
    }

}
