package com.example.kjaga.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kjaga.domain.repo.MainRepository

class HistoryViewModel(private val repository: MainRepository) : ViewModel() {

    fun getHistory(date:String, token:String) = repository.getHistory(date, token)

}