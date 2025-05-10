package com.example.dopatox.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dopatox.databinding.FragmentChallengesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ChallengesFragment : Fragment() {
    lateinit var binding: FragmentChallengesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengesBinding.inflate(layoutInflater)
        return binding.root
    }
}