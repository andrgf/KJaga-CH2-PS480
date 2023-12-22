package com.example.kjaga.data.auth


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("error")
    val error: String
)