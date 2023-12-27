package com.example.kjaga.ui.auth.register

import androidx.lifecycle.ViewModel
import com.example.kjaga.data.auth.Register
import com.example.kjaga.domain.repo.AuthRepository

class RegisterViewModel (private val repository: AuthRepository) : ViewModel() {

    fun registerUser(
        email: String,
        name: String,
        password: String,
        confirmPassword: String
    ) = repository.register(email, name, password, confirmPassword)
}