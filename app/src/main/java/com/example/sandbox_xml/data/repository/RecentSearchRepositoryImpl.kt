package com.example.sandbox_xml.data.repository

import com.example.sandbox_xml.data.local.RecentSearchDao
import com.example.sandbox_xml.data.local.mapToRecentSearchList
import com.example.sandbox_xml.domain.model.RecentSearch
import com.example.sandbox_xml.domain.model.mapToRecentSearchEntity
import com.example.sandbox_xml.domain.repository.RecentSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentSearchRepositoryImpl @Inject constructor(
       private val recentSearchDao: RecentSearchDao
) : RecentSearchRepository {

    override suspend fun getAllRecentSearch(): Flow<List<RecentSearch>> {
        return recentSearchDao.getAllRecentSearch().map { it.mapToRecentSearchList() }
    }

    override suspend fun insertRecentSearch(recentSearch: RecentSearch) {
        recentSearchDao.insertRecentSearch(recentSearch.mapToRecentSearchEntity())
    }
}
