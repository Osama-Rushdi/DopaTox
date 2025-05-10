package com.example.dopatox.ui.home.profile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dopatox.databinding.FragmentBadgesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class BadgesFragment : Fragment() {
    lateinit var binding: FragmentBadgesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBadgesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBadgesRecyclerView()
        animateRV(binding.badgesRecyclerView)
    }

    private fun initBadgesRecyclerView() {
        val badgesAdapter = BadgesAdapter()
        binding.badgesRecyclerView.adapter = badgesAdapter
    }

    private fun animateRV(recyclerView: RecyclerView) {
        recyclerView.post {
            recyclerView.smoothScrollBy(500, 0)
            recyclerView.postDelayed({
                recyclerView.smoothScrollBy(-500, 0)
            }, 600)
        }
    }
}