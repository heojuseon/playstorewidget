package com.study.typeui.ui.child

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.study.typeui.adapter.PlayStoreCustomAdapter
import com.study.typeui.dto.PlayStoreContent

abstract class PlayStoreViewBase(context: Context): FrameLayout(context) {
    protected lateinit var view: View
    protected lateinit var customAdapter: PlayStoreCustomAdapter

    abstract fun setAdapter()
    abstract fun getType(): Int

    fun setData(data: List<PlayStoreContent>) {
        customAdapter = PlayStoreCustomAdapter(data, getType())
        setAdapter()
    }
}