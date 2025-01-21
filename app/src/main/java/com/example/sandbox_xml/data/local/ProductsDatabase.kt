package com.example.sandbox_xml.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductsEntity::class], version = 1)
abstract class ProductsDatabase:RoomDatabase() {
    abstract fun productsDao():ProductsDao
}
