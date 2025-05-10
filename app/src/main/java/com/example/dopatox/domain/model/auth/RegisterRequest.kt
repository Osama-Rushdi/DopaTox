package com.example.dopatox.domain.model.auth


data class RegisterRequest(

    val firstName: String = "",

    val lastName: String = "",

    val password: String = "",

    val dateOfBirth: String = "",

    val userName: String = "",

    val email: String = ""
)