package com.example.sandbox_xml.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sandbox_xml.data.model.RemoteRandomMeal

@Database(entities = [RemoteRandomMeal.RemoteMeal::class], version = 1)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}
