package com.example.kjaga.data.food

import com.google.gson.annotations.SerializedName

data class ScanResultResponse(

	@field:SerializedName("foods")
	val foods: List<FoodsItem?>? = null
)

data class FoodScanResult(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String
)

data class PortionScanResult(

	@field:SerializedName("nutrition")
	val nutrition: NutritionScanResult? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class NutritionScanResult(

	@field:SerializedName("protein(g)")
	val proteinG: Double,

	@field:SerializedName("sodium(mg)")
	val sodiumMg: Double,

	@field:SerializedName("cholesterol(mg)")
	val cholesterolMg: Double,

	@field:SerializedName("fiber(g)")
	val fiberG: Double,

	@field:SerializedName("sugar(g)")
	val sugarG: Double,

	@field:SerializedName("kalium(mg)")
	val kaliumMg: Double,

	@field:SerializedName("fat(g)")
	val fatG: Double,

	@field:SerializedName("carbohydrates(g)")
	val carbohydratesG: Double,

	@field:SerializedName("energy(kkal)")
	val energyKkal: Double
)

data class FoodsItem(

	@field:SerializedName("portion")
	val portion: PortionScanResult? = null,

	@field:SerializedName("portion_count")
	val portionCount: Int? = null,

	@field:SerializedName("food")
	val food: FoodScanResult? = null
)
