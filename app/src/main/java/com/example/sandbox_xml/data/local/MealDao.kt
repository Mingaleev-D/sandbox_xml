package com.example.sandbox_xml.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sandbox_xml.data.model.RemoteRandomMeal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: RemoteRandomMeal.RemoteMeal)

//    @Update
//    suspend fun updateMeal(meal: RemoteRandomMeal.RemoteMeal)

    @Delete
    suspend fun deleteMeal(meal: RemoteRandomMeal.RemoteMeal)

    @Query("SELECT * FROM meals_table")
    fun getSavedAllMeals(): Flow<List<RemoteRandomMeal.RemoteMeal>>
}
