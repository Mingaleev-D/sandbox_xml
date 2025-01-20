package com.example.sandbox_xml.data.repository

import com.example.sandbox_xml.data.local.MealDatabase
import com.example.sandbox_xml.data.model.RemoteAllCategories
import com.example.sandbox_xml.data.model.RemotePopularMeal
import com.example.sandbox_xml.data.model.RemoteRandomMeal
import com.example.sandbox_xml.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
       private val apiService: ApiService,
       private val mealDatabase: MealDatabase
) : MealRepository {

    private val mealDB = mealDatabase.mealDao()

    override suspend fun getRandomMeal(): RemoteRandomMeal {
        return apiService.getRandomMeal()
    }

    override suspend fun getPopularMeal(): RemotePopularMeal {
        return apiService.getPopularMeal()
    }

    override suspend fun getAllCategories(): RemoteAllCategories {
        return apiService.getAllCategories()
    }

    override suspend fun getMealById(id: String): RemoteRandomMeal {
        return apiService.getMealById(id)
    }

    override suspend fun saveMeal(meal: RemoteRandomMeal.RemoteMeal) {
        mealDB.insertMeal(meal)
    }

    override suspend fun deleteMeal(meal: RemoteRandomMeal.RemoteMeal) {
        mealDB.deleteMeal(meal)
    }

    override fun getSavedAllMeals()  =
           mealDB.getSavedAllMeals()
}

interface MealRepository {

    //remote
    suspend fun getRandomMeal(): RemoteRandomMeal
    suspend fun getPopularMeal(): RemotePopularMeal
    suspend fun getAllCategories(): RemoteAllCategories
    suspend fun getMealById(id: String): RemoteRandomMeal

    //db
    suspend fun saveMeal(meal: RemoteRandomMeal.RemoteMeal)
    suspend fun deleteMeal(meal: RemoteRandomMeal.RemoteMeal)
    fun getSavedAllMeals(): Flow<List<RemoteRandomMeal.RemoteMeal>>
}
