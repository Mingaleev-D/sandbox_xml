package com.example.sandbox_xml.ui.pages.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_xml.data.model.RemoteAllProductsItem
import com.example.sandbox_xml.domain.repository.Resource
import com.example.sandbox_xml.domain.usecase.GetAllProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
       private val getAllProductUseCase: GetAllProductUseCase
) : ViewModel() {
    private val _products: MutableLiveData<Resource<List<RemoteAllProductsItem>>> = MutableLiveData()
    val products: LiveData<Resource<List<RemoteAllProductsItem>>>
        get() = _products

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            val productsList = getAllProductUseCase()
            _products.postValue(productsList)
        }
    }
}
