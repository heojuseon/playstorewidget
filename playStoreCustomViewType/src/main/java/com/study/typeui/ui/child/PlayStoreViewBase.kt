package com.study.typeui.ui.child

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.study.typeui.adapter.PlayStoreCustomAdapter
import com.study.typeui.dto.PlayStoreContent
import com.study.typeui.interfaces.OnPlayStoreCustomAppItem
import com.study.typeui.util.PlayStoreAppInstall

abstract class PlayStoreViewBase(context: Context): FrameLayout(context) {
    protected lateinit var view: View
    protected lateinit var customAdapter: PlayStoreCustomAdapter

    abstract fun setAdapter()
    abstract fun getType(): Int

    //외부에서 사용할 리스너 함수
    fun setPlayStoreAppItemClickListener(listener: OnPlayStoreCustomAppItem?){
        customAdapter.setAppItemClickListener(listener)
    }

    fun setData(data: List<PlayStoreContent>) {
        customAdapter = PlayStoreCustomAdapter(data, getType())
        setAdapter()
    }
}