package com.example.kjaga.ui.profile

import androidx.lifecycle.ViewModel
import com.example.kjaga.domain.repo.MainRepository

class ProfileViewModel(private val repository: MainRepository) : ViewModel() {

    fun userByToken(token: String) = repository.getUserByToken(token)

}