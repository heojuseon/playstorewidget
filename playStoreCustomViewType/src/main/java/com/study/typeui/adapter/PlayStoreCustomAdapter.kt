package com.study.typeui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.study.typeui.R
import com.study.typeui.config.PlayStoreViewType
import com.study.typeui.databinding.PlaystoreBannerListItemBinding
import com.study.typeui.databinding.PlaystoreIconItemBinding
import com.study.typeui.dto.PlayStoreContent
import com.study.typeui.interfaces.OnPlayStoreCustomAppItem
import com.study.typeui.util.PlayStoreAppInstall

class PlayStoreCustomAdapter(
    private val playStoreContent: List<PlayStoreContent>,
    private val isType: Int
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //click_event 선언
    private var appItemClickListener: OnPlayStoreCustomAppItem? = null
    //click_event set
    fun setAppItemClickListener(appClickListener: OnPlayStoreCustomAppItem?){
        appItemClickListener = appClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        return if (isType == 2){
            val binding = PlaystoreIconItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CustomIconHolder(binding)
        } else {
            val binding = PlaystoreBannerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CustomBannerListHolder(binding)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isType == 2){
            (holder as CustomIconHolder).bind(playStoreContent[position])
        } else {
            (holder as CustomBannerListHolder).bind(playStoreContent[position])
        }
    }

    override fun getItemCount(): Int {
       return playStoreContent.size
    }

    inner class CustomIconHolder(private val binding: PlaystoreIconItemBinding): RecyclerView.ViewHolder(binding.root) {
        //icon app 클릭시 event
        init {
            binding.root.setOnClickListener {
                val appData = playStoreContent[absoluteAdapterPosition]
                val packageName = appData.pkg
                val isInstalled = PlayStoreAppInstall.isPackageInstalled(binding.root.context, packageName)

                appItemClickListener?.onAppClick(appData, isInstalled)
                Log.d("!@!@#!@#", "position: $absoluteAdapterPosition")
            }
        }

        fun bind(playStoreContent: PlayStoreContent) {
            binding.apply {
                Glide.with(binding.root.context)
                    .load(playStoreContent.url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .transform(RoundedCorners(50)) //radius 처리
                    .into(binding.iconImageview)

                iconTitle.text = playStoreContent.title
                if (playStoreContent.category == 1){
                   iconCategory.text = "액션"
                } else {
                   iconCategory.text = "???"
                }
               iconScore.text = playStoreContent.score
            }
        }

    }

    inner class CustomBannerListHolder(private val binding: PlaystoreBannerListItemBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val appData = playStoreContent[absoluteAdapterPosition]
                val packageName = appData.pkg
                val isInstalled = PlayStoreAppInstall.isPackageInstalled(binding.root.context, packageName)

                appItemClickListener?.onAppClick(appData, isInstalled)
                Log.d("!@!@#!@#", "position: $absoluteAdapterPosition + title: ${playStoreContent[absoluteAdapterPosition].title}")

            }
        }
       fun bind(playStoreContent: PlayStoreContent) {
           //imageView Size
           val params = binding.playStoreImageView.layoutParams
           when(isType){
               PlayStoreViewType.BANNER_LIST.ordinal -> {
                   //banner
                   //width 값은 기본 match_parent 로 설정
                   params.height = binding.root.context.resources.getDimensionPixelSize(R.dimen.banner_image_height)
                   binding.textListArea.visibility = View.GONE

               }
               PlayStoreViewType.HORIZONTAL_LIST.ordinal -> {
                   //list
                   params.width = binding.root.context.resources.getDimensionPixelSize(R.dimen.list_image_width)
                   params.height = binding.root.context.resources.getDimensionPixelSize(R.dimen.list_image_height)
                   binding.bannerSmallIconArea.visibility = View.GONE
               }
           }

           val isInstalled = PlayStoreAppInstall.isPackageInstalled(binding.root.context, playStoreContent.pkg)
           if (isInstalled){
               binding.downBtn.text = "열기"
           } else {
               binding.downBtn.text = "Install"
           }

           binding.apply {
               Glide.with(binding.root.context)
                   .load(playStoreContent.url)
                   .transition(DrawableTransitionOptions.withCrossFade())
                   .transform(CenterCrop(),RoundedCorners(50)) //radius 처리
                   .into(binding.playStoreImageView)

               //banner_small_image
               Glide.with(binding.root.context)
                   .load(playStoreContent.url)
                   .transition(DrawableTransitionOptions.withCrossFade())
                   .transform(CenterCrop(),RoundedCorners(40)) //radius 처리
                   .into(binding.bannerSmallImage)

               listTitle.text = playStoreContent.title
               bannerSmallTitle.text = playStoreContent.title
               if (playStoreContent.category == 1){
                   listCategory.text = "액션"
               } else {
                   listCategory.text = "스포츠"
               }
               listScore.text = playStoreContent.score
               bannerSmallScore.text = playStoreContent.score
           }
       }
   }
}