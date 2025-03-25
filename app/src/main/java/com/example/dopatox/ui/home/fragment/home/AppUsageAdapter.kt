package com.example.dopatox.ui.home.fragment.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dopatox.databinding.ItemAppBinding
import com.example.dopatox.domain.model.AppUsage
import com.example.dopatox.ui.utlis.GetAppUsage.allTotalTime

class AppUsageAdapter(
    private var apps: List<AppUsage>,
    private val onAppClick: (app: AppUsage) -> Unit
) :
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
        holder.bind(app, position + 1)
    }

    override fun getItemCount() = apps.size


    inner class ViewHolder(val binding: ItemAppBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(app: AppUsage, position: Int) {
            Log.d("Testo", "getAppUsage: ${app.packageName} : ${app.usageTime}")
            binding.appId.text = position.toString()
            binding.circularProgressBar.progress = getPercentage(app)
            binding.percent.text = String.format("%.1f%%", getPercentage(app))
            binding.appName.text = app.appName
            binding.hours.text = app.hours.toString()
            binding.min.text = app.minutes.toString()
            binding.appIcon.setImageDrawable(app.icon)
            binding.root.setOnClickListener {
                onAppClick(app)
            }
        }
    }

    private fun getPercentage(app: AppUsage): Float {
        return (app.usageTime.toFloat() / allTotalTime) * 100
    }

    fun submitList(apps: List<AppUsage>) {
        this.apps = apps
    }
}