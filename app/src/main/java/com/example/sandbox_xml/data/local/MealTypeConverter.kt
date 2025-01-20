package com.example.sandbox_xml.data.local

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MealTypeConverter {

    @TypeConverter
    fun fromAnyToString(value: Any?): String {
        if(value == null) return ""
        return value.toString()
    }

    @TypeConverter
    fun fromStringToAny(value: String?): Any {
        if(value == null) return ""
        return value
    }
}
