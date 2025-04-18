package com.example.dopatox.ui.home.profile.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dopatox.databinding.BadgeItemLayoutBinding
import com.example.dopatox.domain.model.BadgeItem

class BadgesAdapter(val badges: List<BadgeItem> = Constants.demoBadges) :
    RecyclerView.Adapter<BadgesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            BadgeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val badge = badges[position]
        holder.bind(badge)
    }

    override fun getItemCount(): Int = badges.size

    inner class ViewHolder(val binding: BadgeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(badge: BadgeItem) {
            binding.badgeImage.setImageResource(badge.badgeImage)
            binding.badgeTitle.text = badge.badgeTitle
            binding.challengeTime.text = badge.challengeTime
            binding.challengeDate.text = badge.challengeDate
        }
    }
}