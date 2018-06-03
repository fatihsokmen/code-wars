package com.github.fatihsokmen.codewars.search.adapter


import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.fatihsokmen.codewars.data.UserDomain
import com.github.fatihsokmen.codewars.search.viewholder.BaseViewHolderFactory
import com.github.fatihsokmen.codewars.search.viewholder.BaseViewHolder
import javax.inject.Inject

class SearchResultsAdapter @Inject constructor(
        private val viewHolderFactories: Map<@JvmSuppressWildcards Int, @JvmSuppressWildcards BaseViewHolderFactory.Builder>)
    : RecyclerView.Adapter<BaseViewHolder>() {

    private var data = emptyList<UserDomain>()

    fun setData(data: List<UserDomain>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_RECENT_ITEM else TYPE_SEARCH_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return viewHolderFactories[viewType]!!.parentView(parent).build().createViewHolder()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (position != 0) holder.bind(data[position - 1])
    }

    override fun getItemCount(): Int {
        return data.size + 1
    }

    companion object {
        const val TYPE_RECENT_ITEM = 0
        const val TYPE_SEARCH_ITEM = 1
    }
}
