package com.example.dopatox.domain.model.challenge

data class ChallengeResponse(

    val duration: String = "",
    val createdAt: String = "",
    val endDate: String = "",
    val description: String = "",
    val id: Int,
    val state: Int = 0,
    val title: String = "",
    val startDate: String = ""
)