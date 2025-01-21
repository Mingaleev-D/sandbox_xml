package com.example.sandbox_xml.domain.repository

import com.example.sandbox_xml.data.model.RemoteAllProductsItem

interface ProductRepository {

    suspend fun getAllProducts(): Resource<List<RemoteAllProductsItem>>
    suspend fun getAllProductsCache(): List<RemoteAllProductsItem>
    suspend fun insertAllProductsCache(products: List<RemoteAllProductsItem>)
}

sealed class Resource <out R> {
    data class Success<out T>(val data: T): Resource<T>()
    data class Error(val error: Exception): Resource<Nothing>()
    object Loading: Resource<Nothing>()
}
