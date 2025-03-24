package com.example.dopatox.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dopatox.databinding.ItemAppShimmerBinding

class AppShimmerAdapter : RecyclerView.Adapter<AppShimmerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemAppShimmerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
    }

    override fun getItemCount(): Int = 10

    inner class ViewHolder(binding: ItemAppShimmerBinding) : RecyclerView.ViewHolder(binding.root)
}