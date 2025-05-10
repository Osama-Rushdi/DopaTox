package com.example.dopatox.ui.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.dopatox.R
import com.example.dopatox.domain.model.auth.RegisterRequest
import com.example.dopatox.domain.model.auth.other.LogoutRequest
import com.example.dopatox.domain.model.auth.other.VerifyCodeRequest
import com.example.dopatox.data.repository.view_model.AuthViewModel
import com.example.dopatox.data.repository.view_model.ChallengeViewModel
import com.example.dopatox.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private val authViewModel: AuthViewModel by viewModels()
    private val challengeViewModel: ChallengeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        allObserveTest()
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.fragment_container)
        setupActionBarWithNavController(navController)
    }

    private fun allObserveTest() {
        authViewModel.register(RegisterRequest())
        authViewModel.verifyCode(VerifyCodeRequest())
        authViewModel.logout(LogoutRequest())
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}