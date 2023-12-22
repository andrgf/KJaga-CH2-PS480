package com.example.kjaga.data.food


import com.google.gson.annotations.SerializedName

data class Portion(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("nutrition")
    val nutrition: Nutrition
)