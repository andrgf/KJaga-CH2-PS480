package com.example.kjaga.data.food


import com.google.gson.annotations.SerializedName

data class Nutrition(
    @SerializedName("carbohydrates(g)")
    val carbohydratesg: Double,
    @SerializedName("cholesterol(mg)")
    val cholesterolmg: Int,
    @SerializedName("energy(kkal)")
    val energykkal: Int,
    @SerializedName("fat(g)")
    val fatg: Double,
    @SerializedName("fiber(g)")
    val fiberg: Double,
    @SerializedName("kalium(mg)")
    val kaliummg: Int,
    @SerializedName("protein(g)")
    val proteing: Double,
    @SerializedName("sodium(mg)")
    val sodiummg: Int,
    @SerializedName("sugar(g)")
    val sugarg: Double
)