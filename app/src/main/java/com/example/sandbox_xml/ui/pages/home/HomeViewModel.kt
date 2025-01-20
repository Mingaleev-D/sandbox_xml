package com.example.sandbox_xml.ui.pages.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_xml.data.model.RemoteAllCategories
import com.example.sandbox_xml.data.model.RemotePopularMeal
import com.example.sandbox_xml.data.model.RemoteRandomMeal
import com.example.sandbox_xml.data.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
       private val mealRepository: MealRepository
) : ViewModel() {

    private val _getRandomMealLiveData = MutableLiveData<RemoteRandomMeal>()
    val getRandomMealLiveData get() = _getRandomMealLiveData
    private val _getOverPopular = MutableLiveData<List<RemotePopularMeal.RemoteOverMeal>>()
    val getOverPopular get() = _getOverPopular
    private val _getAllCategories =
           MutableStateFlow<List<RemoteAllCategories.RemoteCategory>>(emptyList())
    val getAllCategories get() = _getAllCategories

    init {
        getRandomMeal()
        getOverPopular()
        fetchAllCategories()
    }

    private fun getRandomMeal() {
        viewModelScope.launch {
            try {
                _getRandomMealLiveData.value = mealRepository.getRandomMeal()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    private fun getOverPopular() {
        viewModelScope.launch {
            try {
                _getOverPopular.value = (mealRepository.getPopularMeal().meals?.toList() as List<RemotePopularMeal.RemoteOverMeal>?)!!
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    private fun fetchAllCategories() {
        viewModelScope.launch {
            try {
                _getAllCategories.emit((mealRepository.getAllCategories().categories?.toList() as List<RemoteAllCategories.RemoteCategory>?)!!)
                //_getAllCategories.value = (mealRepository.getAllCategories().categories?.toList() as List<RemoteAllCategories.RemoteCategory>?)!!
            } catch (
                   e: Exception
            ) {
                println(e)
            }
        }
    }
}
