package com.example.dopatox.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.dopatox.R
import com.example.dopatox.databinding.ActivityOnBoardingBinding
import com.example.dopatox.ui.permission.PermissionActivity
import com.example.trendify.ui.onboarding.OnboardingAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OnboardingAdapter(Constants.boards)
        val viewPager = binding.viewPager
        viewPager.adapter = adapter
        val indicator = findViewById<DotsIndicator>(R.id.dotsIndicator)
        val intent = Intent(this, PermissionActivity::class.java)
        indicator.attachTo(viewPager)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.startBtn.text = getString(R.string.get_started)
                } else {
                    binding.startBtn.text = getString(R.string.next)
                }
                binding.startBtn.setOnClickListener {
                    if(position == Constants.boards.size - 1){
                        startActivity(intent)
                        finish()
                    }else{
                        viewPager.currentItem = position + 1
                    }
                }
            }
        })
    }
}