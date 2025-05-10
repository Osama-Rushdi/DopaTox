package com.example.dopatox.domain.model.auth.other


data class AuthResponse(

	val failedMessage: String = "",

	val email: String = "",

	val isSuccess: Boolean
)