package com.example.dopatox.domain.model.usage

data class UserUsageRequest(

    val logDate: String = "",
    val appUsages: AppUsages,
    val userId: String

)