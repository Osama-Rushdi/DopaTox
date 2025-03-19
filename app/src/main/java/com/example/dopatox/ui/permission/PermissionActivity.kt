package com.example.dopatox.ui.permission

import android.app.AppOpsManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.example.dopatox.R
import com.example.dopatox.databinding.ActivityPermissionBinding
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import www.sanju.motiontoast.R as TR
import androidx.core.net.toUri
import com.example.dopatox.ui.auth.AuthActivity

class PermissionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPermissionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {

        // skip permission
        binding.skipBtn.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }

        // start button
        binding.startBtn.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }

        // Usage permission
        binding.usagePermission.setOnClickListener {
            if (!isUsageAccessGranted()) {
                binding.done1.isVisible = false
                requestUsageAccess()
            } else {
                binding.done1.isVisible = true
                MotionToast.darkToast(
                    this,
                    getString(R.string.success),
                    getString(R.string.usage_permission_granted),
                    MotionToastStyle.SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    ResourcesCompat.getFont(this, TR.font.montserrat_bold)
                )
            }
        }

        // Display over permission
        binding.displayPermission.setOnClickListener {
            if (!isDisplayOverPermissionGranted()) {
                binding.done2.isVisible = false
                requestDisplayOverPermission()
            } else {
                binding.done2.isVisible = true
                MotionToast.darkToast(
                    this,
                    getString(R.string.success),
                    getString(R.string.display_over_permission_granted),
                    MotionToastStyle.SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    ResourcesCompat.getFont(this, TR.font.montserrat_bold)
                )
            }
        }

        // Background permission
        binding.backgroundPermission.setOnClickListener {
            // TODO add background permission logic
        }

    }

    fun isUsageAccessGranted(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val appOps = getSystemService(APP_OPS_SERVICE) as AppOpsManager
            val mode = appOps.unsafeCheckOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(),
                packageName
            )
            return mode == AppOpsManager.MODE_ALLOWED
        }
        return true
    }

    fun requestUsageAccess() {
        val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun isDisplayOverPermissionGranted(): Boolean {
        return Settings.canDrawOverlays(this)
    }

    fun requestDisplayOverPermission() {
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            "package:$packageName".toUri()
        )
        startActivity(intent)
    }

}