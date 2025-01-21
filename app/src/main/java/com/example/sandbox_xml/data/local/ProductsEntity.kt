package com.example.sandbox_xml.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
data class ProductsEntity(
       @PrimaryKey
       val id: String,
       val title: String,
       val price: Double,
       val description: String,
       val category: String,
       val imageURL: String,
       val rating: Double,
)
