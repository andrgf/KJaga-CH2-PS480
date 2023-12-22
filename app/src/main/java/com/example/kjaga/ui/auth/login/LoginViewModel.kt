package com.example.kjaga.ui.auth.login

import androidx.lifecycle.ViewModel
import com.example.kjaga.domain.repo.AuthRepository

class LoginViewModel (private val repository: AuthRepository) : ViewModel() {

    fun loginUser(email: String, password: String) = repository.login(email, password)

}