package com.example.dopatox.data.model.auth.other

import com.google.gson.annotations.SerializedName

data class VerifyCodeRequest(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)