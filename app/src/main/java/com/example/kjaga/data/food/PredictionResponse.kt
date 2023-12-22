package com.example.kjaga.data.food

import com.google.gson.annotations.SerializedName

data class PredictionResponse(

	@field:SerializedName("content_type")
	val contentType: String,

	@field:SerializedName("prediction_id")
	val predictionId: String,

	@field:SerializedName("url")
	val url: String
)
