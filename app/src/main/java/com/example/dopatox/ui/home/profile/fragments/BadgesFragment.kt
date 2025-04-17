package com.example.dopatox.ui.home.profile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dopatox.databinding.FragmentBadgesBinding

class BadgesFragment : Fragment() {
    lateinit var binding: FragmentBadgesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBadgesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}