package com.example.sandbox_xml.ui.pages.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_xml.data.model.RemoteAllProductsItem
import com.example.sandbox_xml.domain.model.RecentSearch
import com.example.sandbox_xml.domain.repository.Resource
import com.example.sandbox_xml.domain.usecase.GetAllProductUseCase
import com.example.sandbox_xml.domain.usecase.GetAllRecentSearchsUseCase
import com.example.sandbox_xml.domain.usecase.SaveRecentSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
       private val getAllProductUseCase: GetAllProductUseCase,
       private val getAllRecentSearchUseCase: GetAllRecentSearchsUseCase,
       private val saveRecentSearchUseCase: SaveRecentSearchUseCase
) : ViewModel() {

    private val _products: MutableLiveData<Resource<List<RemoteAllProductsItem>>> = MutableLiveData()
    val products: LiveData<Resource<List<RemoteAllProductsItem>>>
        get() = _products

    private val _recentSearch: MutableLiveData<List<RecentSearch>> = MutableLiveData()
    val recentSearch: LiveData<List<RecentSearch>>
        get() = _recentSearch

    init {
        getAllProducts()
        getAllRecentSearch()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            val productsList = getAllProductUseCase()
            _products.postValue(productsList)
        }
    }

    private fun getAllRecentSearch() {
        viewModelScope.launch {
            getAllRecentSearchUseCase().onEach {
                _recentSearch.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun saveRecentSearch(recentSearch: RecentSearch) {
        viewModelScope.launch {
            saveRecentSearchUseCase(recentSearch)
        }
    }
}
