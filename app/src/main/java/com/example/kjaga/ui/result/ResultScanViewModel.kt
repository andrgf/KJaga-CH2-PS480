package com.example.kjaga.ui.result

import androidx.lifecycle.ViewModel
import com.example.kjaga.domain.repo.MainRepository

class ResultScanViewModel(private val repository: MainRepository) : ViewModel() {

    fun getScanResult(predictId: String) = repository.getScanResult(predictId)
}