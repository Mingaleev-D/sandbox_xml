package com.example.sandbox_xml.data.remote

import com.example.sandbox_xml.data.model.RemoteAllCategories
import com.example.sandbox_xml.data.model.RemotePopularMeal
import com.example.sandbox_xml.data.model.RemoteRandomMeal
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("random.php")
    suspend fun getRandomMeal(): RemoteRandomMeal

    @GET("filter.php")
    suspend fun getPopularMeal(
           @Query("c") category: String = "Seafood"
    ): RemotePopularMeal

    @GET("categories.php")
    suspend fun getAllCategories(
    ): RemoteAllCategories
}
