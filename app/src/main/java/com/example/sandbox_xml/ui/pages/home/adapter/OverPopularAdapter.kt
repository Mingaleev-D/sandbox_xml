package com.example.sandbox_xml.ui.pages.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sandbox_xml.data.model.RemotePopularMeal
import com.example.sandbox_xml.databinding.OverRowBinding

class OverPopularAdapter() : RecyclerView.Adapter<OverPopularAdapter.OverViewHolder>() {

    lateinit var onOverItemClick: (RemotePopularMeal.RemoteOverMeal) -> Unit

    inner class OverViewHolder(val binding: OverRowBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffUtil =
           object : DiffUtil.ItemCallback<RemotePopularMeal.RemoteOverMeal>() {
               override fun areItemsTheSame(
                      oldItem: RemotePopularMeal.RemoteOverMeal,
                      newItem: RemotePopularMeal.RemoteOverMeal
               ): Boolean {
                   return oldItem.idMeal == newItem.idMeal
               }

               override fun areContentsTheSame(
                      oldItem: RemotePopularMeal.RemoteOverMeal,
                      newItem: RemotePopularMeal.RemoteOverMeal
               ): Boolean {
                   return oldItem == newItem
               }
           }
    val differ =
           AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(
           parent: ViewGroup,
           viewType: Int
    ): OverViewHolder {
        return OverViewHolder(
               OverRowBinding.inflate(
                      LayoutInflater.from(parent.context),
                      parent,
                      false
               )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(
           holder: OverViewHolder,
           position: Int
    ) {
        val data = differ.currentList[position]
        Glide.with(holder.itemView).load(data.strMealThumb).into(holder.binding.overImg)

        holder.itemView.setOnClickListener {
            onOverItemClick.invoke(data)
        }

    }
}
