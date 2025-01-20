package com.example.sandbox_xml.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteAllCategories(
    @SerialName("categories")
    val categories: List<RemoteCategory?>? = null
) {
    @Serializable
    data class RemoteCategory(
        @SerialName("idCategory")
        val idCategory: String? = null,
        @SerialName("strCategory")
        val strCategory: String? = null,
        @SerialName("strCategoryDescription")
        val strCategoryDescription: String? = null,
        @SerialName("strCategoryThumb")
        val strCategoryThumb: String? = null
    )
}
