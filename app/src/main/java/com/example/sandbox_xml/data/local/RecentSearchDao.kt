package com.example.sandbox_xml.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchDao {

    @Query("SELECT * FROM recent_search_table")
    fun getAllRecentSearch(): Flow<List<RecentSearchEntity>>

    @Insert(onConflict = REPLACE)
    fun insertRecentSearch(recentSearchEntity: RecentSearchEntity)
}
