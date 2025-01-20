package com.example.sandbox_xml.ui.pages.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_xml.data.model.RemoteRandomMeal
import com.example.sandbox_xml.data.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
       private val mealRepository: MealRepository
) : ViewModel() {

    private val _getMealInformation = MutableLiveData<RemoteRandomMeal>()
    val getMealInformation: LiveData<RemoteRandomMeal> get() = _getMealInformation

    fun getMealInformation(id: String) {
        viewModelScope.launch {
            try {
                _getMealInformation.value = mealRepository.getMealById(id)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun saveMeal(meal: RemoteRandomMeal.RemoteMeal) {
        viewModelScope.launch {
            try {
                mealRepository.saveMeal(meal)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun deleteMeal(meal: RemoteRandomMeal.RemoteMeal) {
        viewModelScope.launch {
            try {
                mealRepository.deleteMeal(meal)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

   // fun getSavedAllMeals() = mealRepository.getSavedAllMeals()
}
