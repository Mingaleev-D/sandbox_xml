package com.example.sandbox_xml.ui.pages.search.result.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sandbox_xml.data.model.RemoteAllProductsItem
import com.example.sandbox_xml.databinding.ProductItemBinding

class ProductListAdapter: ListAdapter<RemoteAllProductsItem, ProductListAdapter.ProductViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<RemoteAllProductsItem>() {
        override fun areItemsTheSame(oldItem: RemoteAllProductsItem, newItem: RemoteAllProductsItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RemoteAllProductsItem, newItem: RemoteAllProductsItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class ProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
               ProductItemBinding.inflate(
                      LayoutInflater.from(parent.context),
                      parent,
                      false
               ))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = currentList[position]

        val priceFormatted = "$${product.price}"
        //val brandFormatted = "Marca: ${product.brand.capitalizeWithLocal()}"

        holder.binding.tvName.text = product.title
        holder.binding.tvPrice.text = priceFormatted
        holder.binding.tvBrand.text = product.category

        Glide.with(holder.itemView).load(product.imageURL).into(holder.binding.ivImage)

        holder.binding.clProductParent.setOnClickListener {
            onProductClickListener?.let { click ->
                click(product)
            }
        }
    }

    protected var onProductClickListener : ((RemoteAllProductsItem) -> Unit)? = null

    fun setProductClickListener(listener: (RemoteAllProductsItem) -> Unit){
        onProductClickListener = listener
    }
}
