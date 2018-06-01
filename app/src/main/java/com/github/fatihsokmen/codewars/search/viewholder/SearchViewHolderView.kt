package com.github.fatihsokmen.codewars.search.viewholder

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.datasource.UserDomain
import com.github.fatihsokmen.codewars.dependency.resource.IStringResources
import javax.inject.Inject

class SearchViewHolderView @Inject constructor(
        private val stringResources: IStringResources,
        itemView: View)
    : BaseViewHolder(itemView) {

    @BindView(R.id.name)
    lateinit var nameView: TextView
    @BindView(R.id.rank)
    lateinit var rankView: TextView
    @BindView(R.id.languages)
    lateinit var languagesView: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

    override fun bind(user: UserDomain) {
        nameView.text = user.userName
        rankView.text = stringResources.getString(R.string.user_rank, user.leaderboardPosition)

        val languages = StringBuilder()
        for (language in user.ranks.languages.keys) {
            languages.append("\u2022").append(language).append("  ")
        }
        languagesView.text = languages.toString()
    }
}