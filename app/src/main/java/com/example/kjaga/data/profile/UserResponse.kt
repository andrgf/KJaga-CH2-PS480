package com.example.kjaga.data.profile


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("user")
    val userByToken: UserByToken
)