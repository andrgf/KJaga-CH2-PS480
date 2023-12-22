package com.example.kjaga.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kjaga.data.food.Food
import com.example.kjaga.domain.api.UiState
import com.example.kjaga.domain.repo.MainRepository

class SearchViewModel(private val repository: MainRepository) : ViewModel() {

    fun getSearch(query: String) = repository.getSearch(query)
}