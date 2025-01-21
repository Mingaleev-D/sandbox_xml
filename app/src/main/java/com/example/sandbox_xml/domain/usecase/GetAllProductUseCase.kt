package com.example.sandbox_xml.domain.usecase

import com.example.sandbox_xml.data.model.RemoteAllProductsItem
import com.example.sandbox_xml.domain.repository.ProductRepository
import com.example.sandbox_xml.domain.repository.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(
       private val repository: ProductRepository
) {

    suspend operator fun invoke(): Resource<List<RemoteAllProductsItem>> {
        return withContext(Dispatchers.IO) {
            val networkCall = repository.getAllProducts()

            if (networkCall is Resource.Error) {
                Resource.Error(networkCall.error)
            }

            networkCall as Resource.Success
            repository.insertAllProductsCache(networkCall.data)
            val productList = repository.getAllProductsCache()
            Resource.Success(productList)
        }
    }
}
