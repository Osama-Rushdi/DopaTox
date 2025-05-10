package com.example.dopatox.data.model.challenge

import com.google.gson.annotations.SerializedName

data class ChallengeRequest(

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)