package com.study.typeui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.study.typeui.databinding.ViewpagerGridItemBinding
import com.study.typeui.dto.PlayStoreContent

class PlayStoreGridAdapter(
    private val playStoreContent: List<List<PlayStoreContent>>
): RecyclerView.Adapter<PlayStoreGridAdapter.GridViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayStoreGridAdapter.GridViewHolder {

        val binding = ViewpagerGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayStoreGridAdapter.GridViewHolder, position: Int) {
        holder.bind(playStoreContent[position])
    }

    override fun getItemCount(): Int {
        return playStoreContent.size
    }

    inner class GridViewHolder(private val binding: ViewpagerGridItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(playStoreContent: List<PlayStoreContent>) {
            binding.customGridRecyclerview.layoutManager = GridLayoutManager(binding.root.context, 3, RecyclerView.HORIZONTAL, false)
            binding.customGridRecyclerview.adapter = GridAdapter(playStoreContent)
        }
    }
}