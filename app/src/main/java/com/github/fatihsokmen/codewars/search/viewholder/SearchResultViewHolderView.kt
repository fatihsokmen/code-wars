package com.github.fatihsokmen.codewars.search.viewholder

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.datasource.UserDomain
import javax.inject.Inject

class SearchResultViewHolderView @Inject constructor(itemView: View)
    : SearchResultViewHolder(itemView) {

    @BindView(R.id.name)
    lateinit var nameView: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

    override fun bind(user: UserDomain) {
        nameView.text = user.userName
    }

    @OnClick(R.id.name)
    fun onUserSelected() {
    }
}