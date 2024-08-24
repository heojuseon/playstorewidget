package com.study.typeui.ui.child

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.study.typeui.R
import com.study.typeui.config.PlayStoreViewType

class PlayStoreViewIcon(context: Context): PlayStoreViewBase(context) {
    private val recyclerView: RecyclerView
    private val pagerSnapHelper = PagerSnapHelper()

    init {
        view = inflate(context, R.layout.custom_list_rv_layout, this)
        recyclerView = view.findViewById(R.id.custom_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    override fun setAdapter() {
        recyclerView.adapter = customAdapter
        pagerSnapHelper.attachToRecyclerView(recyclerView)
    }

    override fun getType(): Int {
        return PlayStoreViewType.ICON_LIST.ordinal
    }
}