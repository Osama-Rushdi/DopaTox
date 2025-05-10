package com.example.dopatox.domain.model.auth.other

data class VerifyCodeRequest(

	val code: String,
	val email: String
)