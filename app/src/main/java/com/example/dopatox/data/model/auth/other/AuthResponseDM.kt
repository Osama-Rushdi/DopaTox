package com.example.dopatox.data.model.auth.other

import com.google.gson.annotations.SerializedName

data class AuthResponseDM(

	@field:SerializedName("faildMessage")
	val faildMessage: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)