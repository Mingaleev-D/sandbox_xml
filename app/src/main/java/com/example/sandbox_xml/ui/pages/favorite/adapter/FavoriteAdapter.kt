package com.example.sandbox_xml.ui.pages.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sandbox_xml.data.model.RemoteRandomMeal
import com.example.sandbox_xml.databinding.FavoriteItemBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(val binding: FavoriteItemBinding) :
           RecyclerView.ViewHolder(binding.root)

    private val diffUtil =
           object : DiffUtil.ItemCallback<RemoteRandomMeal.RemoteMeal>() {
               override fun areItemsTheSame(
                      oldItem: RemoteRandomMeal.RemoteMeal,
                      newItem: RemoteRandomMeal.RemoteMeal
               ): Boolean {
                   return oldItem.idMeal == newItem.idMeal
               }

               override fun areContentsTheSame(
                      oldItem: RemoteRandomMeal.RemoteMeal,
                      newItem: RemoteRandomMeal.RemoteMeal
               ): Boolean {
                   return oldItem == newItem
               }
           }
    val differ =
           AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(
           parent: ViewGroup,
           viewType: Int
    ): FavoriteViewHolder {
        return FavoriteViewHolder(
               FavoriteItemBinding.inflate(
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
           holder: FavoriteViewHolder,
           position: Int
    ) {
        val data = differ.currentList[position]
        Glide.with(holder.itemView).load(data.strMealThumb).into(holder.binding.favoriteImg)
        holder.binding.favoriteNameTxt.text = data.strCategory
    }
}
