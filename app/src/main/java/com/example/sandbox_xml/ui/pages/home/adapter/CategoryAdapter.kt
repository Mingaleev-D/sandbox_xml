package com.example.sandbox_xml.ui.pages.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sandbox_xml.data.model.RemoteAllCategories
import com.example.sandbox_xml.databinding.CategoriesItemBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: CategoriesItemBinding) :
           RecyclerView.ViewHolder(binding.root)

    private val diffUtil =
           object : DiffUtil.ItemCallback<RemoteAllCategories.RemoteCategory>() {
               override fun areItemsTheSame(
                      oldItem: RemoteAllCategories.RemoteCategory,
                      newItem: RemoteAllCategories.RemoteCategory
               ): Boolean {
                   return oldItem.idCategory == newItem.idCategory
               }

               override fun areContentsTheSame(
                      oldItem: RemoteAllCategories.RemoteCategory,
                      newItem: RemoteAllCategories.RemoteCategory
               ): Boolean {
                   return oldItem == newItem
               }
           }
    val differ =
           AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(
           parent: ViewGroup,
           viewType: Int
    ): CategoryViewHolder {
        return CategoryViewHolder(
               CategoriesItemBinding.inflate(
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
           holder: CategoryViewHolder,
           position: Int
    ) {
        val data = differ.currentList[position]
        Glide.with(holder.itemView).load(data.strCategoryThumb).into(holder.binding.categoriesImg)
        holder.binding.categoriesTxt.text = data.strCategory
    }
}
