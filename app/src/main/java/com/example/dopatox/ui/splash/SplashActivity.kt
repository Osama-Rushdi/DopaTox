package com.example.dopatox.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.dopatox.R
import com.example.dopatox.databinding.ActivitySplashBinding
import com.example.dopatox.ui.onboarding.OnBoardingActivity


class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val popOutAnimation = Constants.runAnimation(this, R.anim.fade_in)
        binding.logo.startAnimation(popOutAnimation)

        Handler().postDelayed({
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }, 3000)
    }
}