package com.example.sandbox_xml.data.mappers

import com.example.sandbox_xml.data.local.ProductsEntity
import com.example.sandbox_xml.data.model.RemoteAllProductsItem
import com.example.sandbox_xml.domain.model.Products

fun RemoteAllProductsItem.toProductsEntity(): ProductsEntity {
    return ProductsEntity(
           id = this.id.orEmpty() ?: "",
           title = this.title ?: "Unknown Title",
           price = this.price ?: 0.0,
           description = this.description ?: "No description available",
           category = this.category ?: "Uncategorized",
           imageURL = this.imageURL ?: "",
           rating = this.rating ?: 0.0
    )
}

fun List<RemoteAllProductsItem>.toProductsEntityList(): List<ProductsEntity> {
    return map { it.toProductsEntity() }
}

fun ProductsEntity.toProductsRemote(): RemoteAllProductsItem {
    return RemoteAllProductsItem(
           id = this.id,
           title = this.title ,
           price = this.price ,
           description = this.description,
           category = this.category ,
           imageURL = this.imageURL ,
           rating = this.rating
    )
}

fun List<ProductsEntity>.toProductsRemote(): List<RemoteAllProductsItem> {
    return map { it.toProductsRemote() }
}
