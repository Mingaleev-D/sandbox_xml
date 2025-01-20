package com.example.sandbox_xml.ui.pages.favorite

import androidx.lifecycle.ViewModel
import com.example.sandbox_xml.data.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
       private val mealRepository: MealRepository
) : ViewModel() {

    fun getSavedAllMeals() = mealRepository.getSavedAllMeals()

}
