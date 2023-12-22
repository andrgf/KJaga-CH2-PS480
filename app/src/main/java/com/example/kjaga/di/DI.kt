package com.example.kjaga.di

import android.content.Context
import com.example.kjaga.domain.api.ApiConfig
import com.example.kjaga.domain.repo.AuthRepository
import com.example.kjaga.domain.repo.MainRepository

object DI {

    fun provideAuthRepository(context: Context): AuthRepository {
        val apiService = ApiConfig.getApiService(context)
        return AuthRepository.getInstance(apiService)
    }

    fun provideMainRepository(context: Context) : MainRepository {
        val apiService = ApiConfig.getApiService(context)
        return MainRepository.getInstance(apiService, context)
    }

}