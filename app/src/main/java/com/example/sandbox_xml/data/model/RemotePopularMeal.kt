package com.example.sandbox_xml.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePopularMeal(
    @SerialName("meals")
    val meals: List<RemoteOverMeal?>? = null
) {
    @Serializable
    data class RemoteOverMeal(
        @SerialName("idMeal")
        val idMeal: String? = null,
        @SerialName("strMeal")
        val strMeal: String? = null,
        @SerialName("strMealThumb")
        val strMealThumb: String? = null
    )
}
