package com.study.typeui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.Transition
import com.study.typeui.R
import com.study.typeui.databinding.PlaystoreCustomItemBinding
import com.study.typeui.dto.PlayStoreContent
import com.study.typeui.dto.PlayStoreFake

class PlayStoreCustomAdapter(
    private val playStoreContent: List<PlayStoreContent>,
    private val isType: Int
): RecyclerView.Adapter<PlayStoreCustomAdapter.CustomHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayStoreCustomAdapter.CustomHolder {
        val binding = PlaystoreCustomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayStoreCustomAdapter.CustomHolder, position: Int) {
        holder.bind(playStoreContent[position])
    }

    override fun getItemCount(): Int {
       return playStoreContent.size
    }

    inner class CustomHolder(private val binding: PlaystoreCustomItemBinding): RecyclerView.ViewHolder(binding.root) {
       fun bind(playStoreContent: PlayStoreContent) {

           //imageView Size
           val params = binding.playStoreImageView.layoutParams
           when(isType){
               0 -> {
                   //banner
                   params.width = binding.root.context.resources.getDimensionPixelSize(R.dimen.banner_image_width)
                   params.height = binding.root.context.resources.getDimensionPixelSize(R.dimen.banner_image_height)
                   binding.textArea.visibility = View.GONE
               }
               1 -> {
                   //list
                   params.width = binding.root.context.resources.getDimensionPixelSize(R.dimen.list_image_width)
                   params.height = binding.root.context.resources.getDimensionPixelSize(R.dimen.list_image_height)
               }
           }

           binding.apply {
               Glide.with(binding.root.context)
                   .load(playStoreContent.url)
                   .transition(DrawableTransitionOptions.withCrossFade())
                   .into(binding.playStoreImageView)

               title.text = playStoreContent.title
               if (playStoreContent.category == 1){
                   category.text = "액션"
               } else {
                   category.text = "???"
               }
               score.text = playStoreContent.score
           }
       }
   }
}