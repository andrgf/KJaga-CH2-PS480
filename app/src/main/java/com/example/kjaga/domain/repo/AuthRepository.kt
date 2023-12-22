package com.example.kjaga.domain.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.kjaga.data.auth.LoginResponse
import com.example.kjaga.data.auth.RegisterResponse
import com.example.kjaga.domain.api.ApiService
import com.example.kjaga.domain.api.UiState

class AuthRepository private constructor(private val apiService: ApiService) {

    fun register(
        email: String,
        name: String,
        password: String,
        confirmPassword: String
    ): LiveData<UiState<RegisterResponse>> = liveData {
        emit(UiState.Loading)
        try {
            val response = apiService.register(email, name, password, confirmPassword)
            emit(UiState.Success(response))
        } catch (e: Exception) {
            Log.e("RegisterViewModel", "register: ${e.message.toString()}")
            emit(UiState.Error(e.message.toString()))
        }
    }

    fun login(
        email: String,
        password: String
    ): LiveData<UiState<LoginResponse>> = liveData {
        emit(UiState.Loading)
        try {
            val response = apiService.login(email, password)
            emit(UiState.Success(response))
        } catch (e: Exception) {
            Log.e("LoginViewModel", "login: ${e.message.toString()}")
            emit(UiState.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: AuthRepository? = null
        fun getInstance(
            apiService: ApiService
        ): AuthRepository =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(apiService)
            }.also { instance = it }
    }
}