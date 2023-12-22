package com.example.kjaga.data.food


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("foods")
    val foods: List<Food>
)