package com.example.dopatox.data.model.usage

import com.example.dopatox.domain.model.usage.AppUsages
import com.google.gson.annotations.SerializedName

data class UserUsageRequestDM(

    @field:SerializedName("logDate")
	val logDate: String? = null,

    @field:SerializedName("appUsages")
	val appUsages: AppUsages? = null,

    @field:SerializedName("userId")
	val userId: String? = null
)