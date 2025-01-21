package com.example.sandbox_xml.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteAllProductsItem(
       @SerialName("id")
       val id: String? = null,
       @SerialName("category")
       val category: String? = null,
       @SerialName("description")
       val description: String? = null,
       @SerialName("imageURL")
       val imageURL: String? = null,
       @SerialName("price")
       val price: Double? = null,
       @SerialName("rating")
       val rating: Double? = null,
       @SerialName("title")
       val title: String? = null
)

@Serializable
enum class Category(val value: String) {

    @SerialName("electronics")
    Electronics("electronics"),

    @SerialName("jewelery")
    Jewelery("jewelery"),

    @SerialName("men's clothing")
    MenSClothing("men's clothing"),

    @SerialName("women's clothing")
    WomenSClothing("women's clothing");
}
//       @SerialName("availability")
//       val availability: String? = null,
//       @SerialName("productType")
//       val productType: String? = null,
//       @SerialName("ratingCount")
//       val ratingCount: Int? = null,
//       @SerialName("seller")
//       val seller: String? = null,
//       @SerialName("source")
//       val source: String? = null,
