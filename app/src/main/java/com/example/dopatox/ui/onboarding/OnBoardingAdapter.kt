package com.example.trendify.ui.onboarding

import Constants
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.dopatox.R
import com.example.dopatox.databinding.BoardItemBinding
import com.example.dopatox.domain.model.BoardItem

class OnboardingAdapter(private val items: List<BoardItem>) :
    Adapter<OnboardingAdapter.BoardViewHolder>() {


    inner class BoardViewHolder(private val binding: BoardItemBinding) : ViewHolder(binding.root) {
        fun bind(item: BoardItem) {
            binding.image.setImageResource(item.image)
            binding.title.text = item.title
            binding.description.text = item.description
            binding.root.startAnimation(Constants.runAnimation(binding.root.context, R.anim.fade_in))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val binding = BoardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val board = items[position]
        holder.bind(board)
    }
}
