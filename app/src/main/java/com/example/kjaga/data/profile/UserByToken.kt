package com.example.kjaga.data.profile


import com.google.gson.annotations.SerializedName

data class UserByToken(
    @SerializedName("age")
    val age: Any,
    @SerializedName("akg_type")
    val akgType: String,
    @SerializedName("birthdate")
    val birthdate: Any,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: Any,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("updated_at")
    val updatedAt: String
)