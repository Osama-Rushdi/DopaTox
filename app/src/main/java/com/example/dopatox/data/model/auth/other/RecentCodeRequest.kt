package com.example.dopatox.data.model.auth.other

import com.google.gson.annotations.SerializedName

data class RecentCodeRequest(

	@field:SerializedName("email")
	val email: String? = null
)