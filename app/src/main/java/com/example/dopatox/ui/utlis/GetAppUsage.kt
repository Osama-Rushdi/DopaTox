package com.example.dopatox.ui.utlis

import Constants.getHours
import Constants.getMinutes
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import com.example.dopatox.domain.model.AppUsage
import com.example.dopatox.ui.utils.PermissionUtils.isUsageAccessGranted
import com.example.dopatox.ui.utils.PermissionUtils.requestUsageAccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar

object GetAppUsage {
    var allTotalTime: Long = 0
    suspend fun getUsageStats(context: Context): List<UsageStats> = withContext(Dispatchers.IO) {
        val usageStatsManager =
            context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val calendar = Calendar.getInstance()
        val endTime = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val startTime = calendar.timeInMillis

        return@withContext usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY, startTime, endTime
        )
    }

    suspend fun getAppUsage(context: Context): List<AppUsage> = withContext(Dispatchers.IO) {
        val packageManager = context.packageManager
        val usageMap = mutableMapOf<String, AppUsage>()

        if (isUsageAccessGranted(context)) {
            val usageStats = getUsageStats(context)
            allTotalTime = 0
            for (app in usageStats) {
                try {
                    val appInfo = packageManager.getApplicationInfo(app.packageName, 0)

                    // to control by system apps usage
//                    if ((appInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0){ }
                    val appLabel = packageManager.getApplicationLabel(appInfo).toString()
                    val appIcon: Drawable = packageManager.getApplicationIcon(appInfo)
                    val totalTime = app.totalTimeInForeground
                    val hours = getHours(totalTime)
                    val minutes = getMinutes(totalTime)
                    if (totalTime > 0) {
                        allTotalTime += totalTime
                        if (usageMap.containsKey(app.packageName)) {
                            usageMap[app.packageName]!!.usageTime += totalTime
                            usageMap[app.packageName]!!.hours += getHours(totalTime)
                            usageMap[app.packageName]!!.minutes += getMinutes(totalTime)
                        } else {
                            usageMap[app.packageName] = AppUsage(
                                app.packageName,
                                appLabel,
                                totalTime,
                                hours,
                                minutes,
                                appIcon
                            )
                        }
                    }
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                }
            }
        } else {
            requestUsageAccess(context)
        }

        return@withContext usageMap.values.sortedByDescending { it.usageTime }
    }
}