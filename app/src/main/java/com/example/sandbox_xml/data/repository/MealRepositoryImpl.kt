package com.example.sandbox_xml.data.repository

import com.example.sandbox_xml.data.model.RemoteAllCategories
import com.example.sandbox_xml.data.model.RemotePopularMeal
import com.example.sandbox_xml.data.model.RemoteRandomMeal
import com.example.sandbox_xml.data.remote.ApiService
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
       private val apiService: ApiService
) : MealRepository {

    override suspend fun getRandomMeal(): RemoteRandomMeal {
        return apiService.getRandomMeal()
    }

    override suspend fun getPopularMeal(): RemotePopularMeal {
        return apiService.getPopularMeal()
    }

    override suspend fun getAllCategories(): RemoteAllCategories {
       return apiService.getAllCategories()
    }
}

interface MealRepository {

    suspend fun getRandomMeal(): RemoteRandomMeal
    suspend fun getPopularMeal(): RemotePopularMeal
    suspend fun getAllCategories(): RemoteAllCategories
}
