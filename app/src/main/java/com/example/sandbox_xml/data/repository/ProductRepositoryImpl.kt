package com.example.sandbox_xml.data.repository

import com.example.sandbox_xml.data.local.ProductsDao
import com.example.sandbox_xml.data.mappers.toProductsEntityList
import com.example.sandbox_xml.data.mappers.toProductsRemote
import com.example.sandbox_xml.data.model.RemoteAllProductsItem
import com.example.sandbox_xml.data.remote.ApiService
import com.example.sandbox_xml.domain.repository.ProductRepository
import com.example.sandbox_xml.domain.repository.Resource
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
       private val apiService: ApiService,
       private val productsDao: ProductsDao
) : ProductRepository {

    override suspend fun getAllProducts(): Resource<List<RemoteAllProductsItem>> {
        return try {
            val productList = apiService.getAllProducts()
            Resource.Success(productList)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun getAllProductsCache(): List<RemoteAllProductsItem> {
        return productsDao.getAllProducts().toProductsRemote()
    }

    override suspend fun insertAllProductsCache(products: List<RemoteAllProductsItem>) {
        productsDao.insertProducts(products.toProductsEntityList())
    }
}
