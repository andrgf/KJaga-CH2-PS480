package com.example.kjaga.domain.api

import com.example.kjaga.data.auth.LoginResponse
import com.example.kjaga.data.auth.Register
import com.example.kjaga.data.auth.RegisterResponse
import com.example.kjaga.data.auth.User
import com.example.kjaga.data.food.HistoryResponse2
import com.example.kjaga.data.food.MealDiariesResponse
import com.example.kjaga.data.food.PredictionResponse
import com.example.kjaga.data.food.ScanResultResponse
import com.example.kjaga.data.food.SearchResponse
import com.example.kjaga.data.profile.UserResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @POST("/users/registerV2")
    suspend fun register(
        @Query("email") email: String,
        @Query("name") name: String,
        @Query("password") password: String,
        @Query("confirmPassword") confirmPassword: String
    ): RegisterResponse

    @POST("/users/loginV2")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): LoginResponse

    @GET("/foods")
    suspend fun search(
        @Query("search") search: String
    ): SearchResponse

    @GET("/meal-diaries")
    suspend fun history(
        @Query("date") date: String,
        @Header("Authorization") token: String,
    ): HistoryResponse2


    @GET("/predict")
    suspend fun getSignedLink(
        @Header("Authorization") token: String,
        @Query("mime_type") mime_type: String,
    ): PredictionResponse

    @GET("predict/{predict_id}")
    suspend fun getResult(
        @Path("predict_id") predictId: String
    ): ScanResultResponse


    @PUT
    suspend fun uploadFile(
        @Url url: String,
        @Body file: RequestBody,
        @Header("Content-Type") content_type: String
    )

    @FormUrlEncoded
    @POST("/meal-diaries")
    suspend fun addMealDiares(
        @Header("Authorization") token: String,
        @Query("date") date: String,
        @Query("mealType") mealType: String,
        @Field("foodId") foodId: Int?,
        @Field("portionId") portionId: Int?,
        @Field("quantity") quantity: Int?
    ) :MealDiariesResponse

    @GET("/users")
    suspend fun getUserByToken(
        @Header("Authorization") token: String
    ): UserResponse
}