package com.example.sandbox_xml.data.remote

import com.example.sandbox_xml.data.model.RemoteAllProductsItem
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getAllProducts(): List<RemoteAllProductsItem>
}
