package com.example.sandbox_xml.domain.usecase

import com.example.sandbox_xml.domain.model.RecentSearch
import com.example.sandbox_xml.domain.repository.RecentSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveRecentSearchUseCase @Inject constructor(
       private val repository: RecentSearchRepository
) {

    suspend operator fun invoke(recentSearch: RecentSearch) {
        return withContext(Dispatchers.IO) {
            repository.insertRecentSearch(recentSearch)
        }
    }
}
