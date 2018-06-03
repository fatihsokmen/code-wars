package com.github.fatihsokmen.codewars.search.adapter


import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.fatihsokmen.codewars.search.UserModel
import com.github.fatihsokmen.codewars.search.viewholder.BaseViewHolder
import com.github.fatihsokmen.codewars.search.viewholder.BaseViewHolderFactory
import javax.inject.Inject

class SearchResultsAdapter @Inject constructor(
        private val viewHolderFactories: Map<@JvmSuppressWildcards Int, @JvmSuppressWildcards BaseViewHolderFactory.Builder>)
    : RecyclerView.Adapter<BaseViewHolder>() {

    private var data = emptyList<UserModel>()

    fun setData(data: List<UserModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return viewHolderFactories[viewType]?.parentView(parent)?.build()?.createViewHolder()
                ?: throw IllegalArgumentException("Unknown view holder type: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
