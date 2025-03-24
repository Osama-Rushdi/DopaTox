package com.example.dopatox.ui.permission

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.dopatox.R
import com.example.dopatox.databinding.ActivityPermissionBinding
import com.example.dopatox.ui.auth.AuthActivity
import com.example.dopatox.ui.utils.PermissionUtils
import com.example.dopatox.ui.utlis.successToast

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
            if (!PermissionUtils.isUsageAccessGranted(this)) {
                binding.done1.isVisible = false
                PermissionUtils.requestUsageAccess(this)
            } else {
                binding.done1.isVisible = true
                successToast(this, getString(R.string.usage_permission_granted))
            }
        }

        // Display over permission
        binding.displayPermission.setOnClickListener {
            if (!PermissionUtils.isDisplayOverPermissionGranted(this)) {
                binding.done2.isVisible = false
                PermissionUtils.requestDisplayOverPermission(this)
            } else {
                binding.done2.isVisible = true
                successToast(this, getString(R.string.display_over_permission_granted))
            }
        }

        // Background permission
        binding.backgroundPermission.setOnClickListener {
            // TODO add background permission logic
        }

    }
}