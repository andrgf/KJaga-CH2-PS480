package com.example.kjaga.data.food

import com.google.gson.annotations.SerializedName

data class MealDiariesResponse(

	@field:SerializedName("inserted")
	val inserted: List<InsertedItem?>? = null,

	@field:SerializedName("notInserted")
	val notInserted: List<NotInsertedItem?>? = null
)

data class InsertedItem(

	@field:SerializedName("quantity")
	val quantity: String? = null,

	@field:SerializedName("portionId")
	val portionId: String? = null,

	@field:SerializedName("foodId")
	val foodId: String? = null
)

data class NotInsertedItem(

	@field:SerializedName("quantity")
	val quantity: String? = null,

	@field:SerializedName("portionId")
	val portionId: String? = null,

	@field:SerializedName("foodId")
	val foodId: String? = null
)