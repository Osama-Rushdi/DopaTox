package com.example.dopatox.data.model.challenge

import com.google.gson.annotations.SerializedName

data class ChallengeErrorResponseDM(

	@field:SerializedName("instance")
	val instance: String? = null,

	@field:SerializedName("additionalProp1")
	val additionalProp1: String? = null,

	@field:SerializedName("additionalProp3")
	val additionalProp3: String? = null,

	@field:SerializedName("detail")
	val detail: String? = null,

	@field:SerializedName("additionalProp2")
	val additionalProp2: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)