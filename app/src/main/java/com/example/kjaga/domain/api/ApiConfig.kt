package com.example.kjaga.domain.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfig {
    companion object {
        fun getApiService(context: Context): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


            val client = OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS) // Set connection timeout
                .readTimeout(60, TimeUnit.SECONDS)    // Set read timeout
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(ChuckerInterceptor(context))
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://kjaga-dev-vpzh6hrtya-et.a.run.app")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}