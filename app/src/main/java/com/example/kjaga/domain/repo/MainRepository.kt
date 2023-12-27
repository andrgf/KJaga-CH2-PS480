package com.example.kjaga.domain.repo

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.kjaga.data.food.HistoryResponse2
import com.example.kjaga.data.food.MealDiariesResponse
import com.example.kjaga.data.food.PredictionResponse
import com.example.kjaga.data.food.ScanResultResponse
import com.example.kjaga.data.food.SearchResponse
import com.example.kjaga.data.profile.UserResponse
import com.example.kjaga.domain.api.ApiService
import com.example.kjaga.domain.api.UiState
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.File

class MainRepository private constructor(
    private val apiService: ApiService,
    private val context: Context
) {


    fun getSearch(query: String): LiveData<UiState<SearchResponse>> =
        liveData {
            emit(UiState.Loading)
            try {
                val response = apiService.search(query)
                emit(UiState.Success(response))
                Log.d("Search", response.toString())
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }
        }

    fun getHistory(date: String, token: String): LiveData<UiState<HistoryResponse2>> = liveData {
        emit(UiState.Loading)
        try {
            val response = apiService.history(date, token)
            emit(UiState.Success(response))
            Log.d("History", "Success")
        } catch (e: Exception) {
            emit(UiState.Error(e.message.toString()))
            Log.d("History", "Failed")
        }
    }

    fun getSignedLink(token: String, mime_type: String): LiveData<UiState<PredictionResponse>> =
        liveData {
            emit(UiState.Loading)
            try {
                val response = apiService.getSignedLink(token, mime_type)
                emit(UiState.Success(response))
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }
        }

//    fun uploadMealsDiaries(token: String, date: String, mealType: String, requestBody: DiaryMeals): LiveData<UiState<MealDiariesResponse>> =
//        liveData {
//            emit(UiState.Loading)
//            try {
//                val response = apiService.addMealDiares(token, date, mealType, requestBody)
//                emit(UiState.Success(response))
//            }catch (e: Exception) {
//                emit(UiState.Error(e.message.toString()))
//            }
//        }

    fun uploadMealsDiaries(token: String, date: String, mealType: String, foodId: Int?,portionId: Int?, quantity: Int): LiveData<UiState<MealDiariesResponse>> =
        liveData {
            emit(UiState.Loading)
            try {
                val response = apiService.addMealDiares(token, date, mealType, foodId, portionId, quantity)
                emit(UiState.Success(response))
            }catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }
        }

    fun uploadImages(url: String, imageFile: File ) = liveData {
        emit(UiState.Loading)
        val body = RequestBody.create("application/octet".toMediaTypeOrNull(), imageFile.readBytes())

        try {
            val resposne = apiService.uploadFile(url,body, "image/jpeg")
            emit(UiState.Success(resposne))

        }catch (e: HttpException){

        }
    }

    fun getScanResult(predictId: String): LiveData<UiState<ScanResultResponse>> =
        liveData {
            emit(UiState.Loading)
            try {
                val response = apiService.getResult(predictId)
//                Log.d("scanResultResponse", response.toString())
                emit(UiState.Success(response))
            } catch (e: Exception) {
                Log.d("ErrorgetScanResult", e.message.toString())
                emit(UiState.Error(e.message.toString()))
            }
        }

    fun getUserByToken(token: String):LiveData<UiState<UserResponse>> =
        liveData {
            emit(UiState.Loading)
            try {
                val response = apiService.getUserByToken(token)
                emit(UiState.Success(response))
            } catch (e: Exception) {
                Log.d("Error User By Token", e.message.toString())
                emit(UiState.Error(e.message.toString()))
            }
        }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: MainRepository? = null
        fun getInstance(
            apiService: ApiService,
            context: Context
        ): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(apiService, context)
            }.also { instance = it }
    }
}