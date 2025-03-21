package com.example.dopatox.ui.home

import Constants.demoApps
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dopatox.databinding.ItemAppBinding
import com.example.dopatox.domain.model.AppUsage

class AppUsageAdapter(private val apps: List<AppUsage> = demoApps) :
    RecyclerView.Adapter<AppUsageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val app = apps[position]
        holder.bind(app)
    }

    override fun getItemCount() = apps.size


    inner class ViewHolder(val binding: ItemAppBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(app: AppUsage) {
            binding.appName.text = app.appName
            binding.hours.text = app.usageTime.toString()
            binding.min.text = app.usageTime.toString()
            binding.appIcon.setImageResource(app.icon)
            binding.circularProgressBar.apply {
                progress = app.usageTime.toFloat()
                roundBorder = true
            }
        }
    }
}