package com.example.dopatox.data.model.auth.other

import com.google.gson.annotations.SerializedName

data class LogoutRequestDM(

	@field:SerializedName("refToken")
	val refToken: String? = null
)