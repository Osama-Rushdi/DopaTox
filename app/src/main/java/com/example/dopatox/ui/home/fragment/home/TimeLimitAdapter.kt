package com.example.dopatox.ui.home.fragment.home

import Constants.timeLimits
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dopatox.databinding.ItemTimeLimitBinding

class TimeLimitAdapter(private val onLimitClick: (limit: Float) -> Unit) :
    RecyclerView.Adapter<TimeLimitAdapter.TimeLimitViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TimeLimitViewHolder {
        val binding =
            ItemTimeLimitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeLimitViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TimeLimitViewHolder,
        position: Int
    ) {
        val limit = timeLimits[position]
        holder.bind(limit!!)
    }

    override fun getItemCount(): Int = timeLimits.size

    inner class TimeLimitViewHolder(val binding: ItemTimeLimitBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(limit: Pair<String, Float>) {
            binding.timeTv.text = limit.first
            binding.root.setOnClickListener {
                onLimitClick(limit.second)
            }
        }
    }
}