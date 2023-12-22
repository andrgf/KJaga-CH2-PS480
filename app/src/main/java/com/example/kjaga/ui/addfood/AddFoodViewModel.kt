package com.example.kjaga.ui.addfood

import androidx.lifecycle.ViewModel
import com.example.kjaga.data.requestBody.DiaryMeals
import com.example.kjaga.domain.repo.MainRepository

class AddFoodViewModel(private val repository: MainRepository) : ViewModel() {

//    fun uploadMealsDiaries(token: String, date: String, mealType: String, requestBody: DiaryMeals) = repository.uploadMealsDiaries(token, date, mealType, requestBody)
fun uploadMealsDiaries(token: String, date: String, mealType: String, foodId: Int?, portionId: Int?, quantity: Int) = repository.uploadMealsDiaries(token, date, mealType, foodId, portionId, quantity)
}