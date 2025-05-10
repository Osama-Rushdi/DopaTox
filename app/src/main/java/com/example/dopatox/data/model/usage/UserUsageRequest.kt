package com.example.dopatox.data.model.usage

import com.google.gson.annotations.SerializedName

data class UserUsageRequest(

    @field:SerializedName("logDate")
	val logDate: String? = null,

    @field:SerializedName("appUsages")
	val appUsages: AppUsages? = null,

    @field:SerializedName("userId")
	val userId: String? = null
)