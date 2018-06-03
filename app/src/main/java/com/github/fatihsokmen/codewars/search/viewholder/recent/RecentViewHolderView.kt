package com.github.fatihsokmen.codewars.search.viewholder.recent

import android.view.View
import com.github.fatihsokmen.codewars.search.UserModel
import com.github.fatihsokmen.codewars.search.viewholder.BaseViewHolder
import javax.inject.Inject

class RecentViewHolderView @Inject constructor(itemView: View)
    : BaseViewHolder(itemView) {

    override fun bind(user: UserModel) {
        print("Not applicable now")
    }
}