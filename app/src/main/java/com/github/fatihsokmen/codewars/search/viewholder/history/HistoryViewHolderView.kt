package com.github.fatihsokmen.codewars.search.viewholder.history

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.challenges.ChallengesActivity
import com.github.fatihsokmen.codewars.dependency.resource.IStringResources
import com.github.fatihsokmen.codewars.search.UserModel
import com.github.fatihsokmen.codewars.search.viewholder.BaseViewHolder
import javax.inject.Inject

class HistoryViewHolderView @Inject constructor(
        private val stringResources: IStringResources,
        itemView: View)
    : BaseViewHolder(itemView) {

    @BindView(R.id.name)
    lateinit var nameView: TextView
    @BindView(R.id.rank)
    lateinit var rankView: TextView
    @BindView(R.id.languages)
    lateinit var languagesView: TextView

    private lateinit var user: UserModel

    init {
        ButterKnife.bind(this, itemView)
    }

    override fun bind(user: UserModel) {
        this.user = user

        nameView.text = user.userName
        languagesView.text = user.languages
        rankView.text = stringResources.getString(R.string.user_rank, user.leaderboardPosition)

    }

    @OnClick(R.id.item_view)
    fun onUserClicked() {
        val context = itemView.context
        val intent = ChallengesActivity.getIntent(context = context, userName = user.userName)
        context.startActivity(intent)
    }

}