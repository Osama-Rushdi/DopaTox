package com.example.dopatox.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dopatox.R
import com.example.dopatox.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleNavigation()
    }

    private fun handleNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationView.itemActiveIndicatorColor = getColorStateList(R.color.light_teal)
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}