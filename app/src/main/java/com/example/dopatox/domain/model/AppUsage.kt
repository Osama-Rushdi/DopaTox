package com.example.dopatox.domain.model

import android.graphics.drawable.Drawable

data class AppUsage(
    val packageName: String,
    val appName: String,
    var usageTime: Long,
    var hours: Int,
    var minutes: Int,
    val icon: Drawable
)
