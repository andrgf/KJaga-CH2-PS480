package com.example.kjaga.ui.history.food

import androidx.lifecycle.ViewModel
import com.example.kjaga.domain.repo.MainRepository

class HistoryFoodViewModel(private val repository: MainRepository) : ViewModel() {
    fun historyFood(date: String, token: String) = repository.getHistory(date, token)
}