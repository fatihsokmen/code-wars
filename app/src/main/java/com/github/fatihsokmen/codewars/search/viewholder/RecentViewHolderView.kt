package com.github.fatihsokmen.codewars.search.viewholder

import android.view.View
import com.github.fatihsokmen.codewars.datasource.UserDomain
import javax.inject.Inject

class RecentViewHolderView @Inject constructor(itemView: View)
    : BaseViewHolder(itemView) {

    override fun bind(user: UserDomain) {
        print("TEST")
    }
}