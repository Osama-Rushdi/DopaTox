package com.example.dopatox.data.model.usage

import com.google.gson.annotations.SerializedName

data class AppUsagesDM(

	@field:SerializedName("additionalProp1")
	val additionalProp1: String? = null,

	@field:SerializedName("additionalProp3")
	val additionalProp3: String? = null,

	@field:SerializedName("additionalProp2")
	val additionalProp2: String? = null
)