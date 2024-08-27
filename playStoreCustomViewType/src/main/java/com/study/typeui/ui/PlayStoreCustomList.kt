package com.study.typeui.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.study.typeui.R
import com.study.typeui.adapter.PlayStoreCustomAdapter
import com.study.typeui.adapter.PlayStoreGridAdapter
import com.study.typeui.config.PlayStoreViewType
import com.study.typeui.dto.PlayStoreContent
import com.study.typeui.interfaces.OnPlayStoreCustomAppItem
import com.study.typeui.ui.child.PlayStoreViewBanner
import com.study.typeui.ui.child.PlayStoreViewBase
import com.study.typeui.ui.child.PlayStoreViewHorizontalList
import com.study.typeui.ui.child.PlayStoreViewIcon

class PlayStoreCustomList(context: Context, attrs: AttributeSet): FrameLayout(context, attrs){
    private val view: View
    private var isType: Int = 0

    private lateinit var baseView: PlayStoreViewBase

    //외부에서 사용할 리스너 함수
    fun setPlayStoreAppItemClickListener(listener: OnPlayStoreCustomAppItem){
        baseView.setPlayStoreAppItemClickListener(listener)
    }

    init {
        view = inflate(context, R.layout.custom_list_layout, this)

        //type select
        //attrs.xml에서 View의 속성 목록 초기화 작업
        //TypedArray 객체
        context.obtainStyledAttributes(attrs, R.styleable.PlayStoreCustomList).apply {
            try {
                isType = getInteger(R.styleable.PlayStoreCustomList_list_type, 0)
            }finally {  //try 블록에서 일어난 일에 관계없이 항상 실행 보장
                recycle()   //TypeArray 객체는 나중에 호출할 시 재사용을 위해 recycle 함수 사용
            }
        }

        setPlayStoreView()
    }

    private fun setPlayStoreView() {
        baseView = when(isType){
            PlayStoreViewType.BANNER_LIST.ordinal -> PlayStoreViewBanner(context)
            PlayStoreViewType.HORIZONTAL_LIST.ordinal -> PlayStoreViewHorizontalList(context)
            PlayStoreViewType.ICON_LIST.ordinal -> PlayStoreViewIcon(context)

            else -> {throw IllegalArgumentException("error_type")}
        }

        addView(baseView)
    }


    fun setJsonData(playStoreContent: List<PlayStoreContent>){
        baseView.setData(playStoreContent)
    }
}