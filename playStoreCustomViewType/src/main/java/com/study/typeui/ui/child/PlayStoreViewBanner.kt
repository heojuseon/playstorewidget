package com.study.typeui.ui.child

import android.content.Context
import androidx.viewpager2.widget.ViewPager2
import com.study.typeui.R
import com.study.typeui.config.PlayStoreViewType

class PlayStoreViewBanner(context: Context): PlayStoreViewBase(context) {
    private val viewPager: ViewPager2

    init {
        view = inflate(context, R.layout.custom_list_vp_layout, this)
        viewPager = view.findViewById(R.id.custom_viewPager)
    }

    override fun setAdapter() {
        viewPager.adapter = customAdapter
    }

    override fun getType(): Int {
        return PlayStoreViewType.BANNER_LIST.ordinal
    }
}