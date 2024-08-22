package com.study.typeui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.study.typeui.databinding.PlaystoreBannerListItemBinding
import com.study.typeui.dto.PlayStoreContent

class GridAdapter(
    private val playStoreContent: List<PlayStoreContent>
) : RecyclerView.Adapter<GridAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridAdapter.ItemViewHolder {
        val binding = PlaystoreBannerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridAdapter.ItemViewHolder, position: Int) {
        holder.bind(playStoreContent[position])
    }

    override fun getItemCount(): Int {
        return playStoreContent.size
    }

    inner class ItemViewHolder(private val binding: PlaystoreBannerListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(playStoreContent: PlayStoreContent) {

            binding.apply {
                Glide.with(binding.root.context)
                    .load(playStoreContent.url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.playStoreImageView)
            }
        }
    }
}