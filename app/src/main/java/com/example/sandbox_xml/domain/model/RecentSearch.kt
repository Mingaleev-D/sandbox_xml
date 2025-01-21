package com.example.sandbox_xml.domain.model

import com.example.sandbox_xml.data.local.RecentSearchEntity

data class RecentSearch(
       val query: String
)

fun RecentSearch.mapToRecentSearchEntity(): RecentSearchEntity {
    return RecentSearchEntity(query = query)
}

fun List<RecentSearch>.mapToRecentSearchListEntity(): List<RecentSearchEntity> {
    return this.map { it.mapToRecentSearchEntity() }
}
