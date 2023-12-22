package com.example.kjaga.data.food


import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("food")
    val food: FoodX,
    @SerializedName("portion")
    val portion: Portion,
    @SerializedName("portion_count")
    val portionCount: Int
)