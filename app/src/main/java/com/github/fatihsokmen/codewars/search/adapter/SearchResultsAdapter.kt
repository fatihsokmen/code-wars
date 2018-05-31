package com.github.fatihsokmen.codewars.search.adapter


import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.fatihsokmen.codewars.datasource.UserDomain
import com.github.fatihsokmen.codewars.search.viewholder.SearchResultViewHolder
import com.github.fatihsokmen.codewars.search.viewholder.SearchResultViewHolderFactory
import javax.inject.Inject

class SearchResultsAdapter @Inject constructor(
        private val viewHolderFactoryBuilder: SearchResultViewHolderFactory.Builder)
    : RecyclerView.Adapter<SearchResultViewHolder>() {

    private var data = emptyList<UserDomain>()

    fun setData(data: List<UserDomain>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return viewHolderFactoryBuilder.parentView(parent).build().createViewHolder()
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
