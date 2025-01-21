package com.example.sandbox_xml.ui.pages.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sandbox_xml.databinding.RecentSearchItemBinding
import com.example.sandbox_xml.domain.model.RecentSearch

class RecentSearchListAdapter :
       ListAdapter<RecentSearch, RecentSearchListAdapter.RecentSearchViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<RecentSearch>() {

        override fun areItemsTheSame(
               oldItem: RecentSearch,
               newItem: RecentSearch
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
               oldItem: RecentSearch,
               newItem: RecentSearch
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class RecentSearchViewHolder(val binding: RecentSearchItemBinding) :
           RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
           parent: ViewGroup,
           viewType: Int
    ): RecentSearchViewHolder {
        return RecentSearchViewHolder(
               RecentSearchItemBinding.inflate(
                      LayoutInflater.from(parent.context),
                      parent,
                      false
               )
        )
    }

    override fun onBindViewHolder(
           holder: RecentSearchViewHolder,
           position: Int
    ) {
        val recentSearch = currentList[position]

        holder.binding.tvRecentSearch.text = recentSearch.query

        holder.binding.clRecentSearchParent.setOnClickListener {
            onRecentSearchClickListener?.let { click ->
                click(recentSearch)
            }
        }
    }

    protected var onRecentSearchClickListener: ((RecentSearch) -> Unit)? = null

    fun setRecentSearchClickListener(listener: (RecentSearch) -> Unit) {
        onRecentSearchClickListener = listener
    }
}
