package com.example.sandbox_xml.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sandbox_xml.domain.model.RecentSearch

@Entity(tableName = "recent_search_table")
data class RecentSearchEntity(
       @PrimaryKey
       val query: String
)

fun RecentSearchEntity.mapToRecentSearch(): RecentSearch {
    return RecentSearch(query = query)
}

fun List<RecentSearchEntity>.mapToRecentSearchList(): List<RecentSearch> {
    return this.map { it.mapToRecentSearch() }
}
