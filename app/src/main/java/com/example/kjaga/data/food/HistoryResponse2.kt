package com.example.kjaga.data.food

import com.google.gson.annotations.SerializedName

data class HistoryResponse2(

	@field:SerializedName("lunch")
	val lunch: Lunch? = null,

	@field:SerializedName("snack")
	val snack: Snack? = null,

	@field:SerializedName("totalNutrition")
	val totalNutrition: TotalNutrition,

	@field:SerializedName("breakfast")
	val breakfast: Breakfast,

	@field:SerializedName("akg")
	val akg: Akg? = null,

	@field:SerializedName("dinner")
	val dinner: Dinner? = null
)

data class Akg(

	@field:SerializedName("protein(g)")
	val proteinG: Int? = null,

	@field:SerializedName("sodium(mg)")
	val sodiumMg: Int? = null,

	@field:SerializedName("cholesterol(mg)")
	val cholesterolMg: Int? = null,

	@field:SerializedName("fiber(g)")
	val fiberG: Int? = null,

	@field:SerializedName("sugar(g)")
	val sugarG: Int? = null,

	@field:SerializedName("kalium(mg)")
	val kaliumMg: Int? = null,

	@field:SerializedName("fat(g)")
	val fatG: Int? = null,

	@field:SerializedName("carbohydrates(g)")
	val carbohydratesG: Int? = null,

	@field:SerializedName("energy(kkal)")
	val energyKkal: Int? = null
)

data class ItemsItem(

	@field:SerializedName("nutrition")
	val nutrition: Nutrition2? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("portion")
	val portion: Portion2? = null,

	@field:SerializedName("food")
	val food: Food2? = null
)

data class Food2(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class Lunch(

	@field:SerializedName("totalNutrition")
	val totalNutrition: TotalNutrition? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class Nutrition2(

	@field:SerializedName("protein(g)")
	val proteinG: Double? = null,

	@field:SerializedName("sodium(mg)")
	val sodiumMg: Double? = null,

	@field:SerializedName("cholesterol(mg)")
	val cholesterolMg: Double? = null,

	@field:SerializedName("fiber(g)")
	val fiberG: Double? = null,

	@field:SerializedName("sugar(g)")
	val sugarG: Double? = null,

	@field:SerializedName("kalium(mg)")
	val kaliumMg: Double? = null,

	@field:SerializedName("fat(g)")
	val fatG: Double? = null,

	@field:SerializedName("carbohydrates(g)")
	val carbohydratesG: Double? = null,

	@field:SerializedName("energy(kkal)")
	val energyKkal: Double? = null
)

data class TotalNutrition(

	@field:SerializedName("protein(g)")
	val proteinG: Double? = null,

	@field:SerializedName("sodium(mg)")
	val sodiumMg: Double? = null,

	@field:SerializedName("cholesterol(mg)")
	val cholesterolMg: Double? = null,

	@field:SerializedName("fiber(g)")
	val fiberG: Double? = null,

	@field:SerializedName("sugar(g)")
	val sugarG: Double? = null,

	@field:SerializedName("kalium(mg)")
	val kaliumMg: Double? = null,

	@field:SerializedName("fat(g)")
	val fatG: Double? = null,

	@field:SerializedName("carbohydrates(g)")
	val carbohydratesG: Double? = null,

	@field:SerializedName("energy(kkal)")
	val energyKkal: Int
)

data class Portion2(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class Snack(

	@field:SerializedName("totalNutrition")
	val totalNutrition: TotalNutrition? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class Breakfast(

	@field:SerializedName("totalNutrition")
	val totalNutrition: TotalNutrition? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem>
)

data class Dinner(

	@field:SerializedName("totalNutrition")
	val totalNutrition: TotalNutrition? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)
