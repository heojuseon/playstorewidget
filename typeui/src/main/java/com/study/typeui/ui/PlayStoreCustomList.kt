package com.study.typeui.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.study.typeui.R
import com.study.typeui.adapter.PlayStoreCustomAdapter
import com.study.typeui.adapter.PlayStoreGridAdapter
//import com.study.typeui.adapter.RecyclerAdapter
import com.study.typeui.dto.PlayStoreContent

class PlayStoreCustomList(context: Context, attrs: AttributeSet): FrameLayout(context, attrs){
    private val view: View
    private val viewPager: ViewPager2
    private val recyclerView: RecyclerView
    private val pagerSnapHelper = PagerSnapHelper()
    private var isType: Int = 0

    private lateinit var customAdapter: PlayStoreCustomAdapter
//    private lateinit var gridAdapter: PlayStoreGridAdapter

    init {
        view = inflate(context, R.layout.custom_list_layout, this)
        viewPager = findViewById(R.id.custom_viewPager)
        recyclerView = findViewById(R.id.custom_recyclerView)

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
        when(isType) {
            0 -> {
                //banner
                viewPager.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            1 -> {
                //list
                viewPager.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                recyclerView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false)
            }
            2 -> {
                //icon
                viewPager.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            }
        }
    }


    fun setJsonData(playStoreContent: List<PlayStoreContent>){
        customAdapter = PlayStoreCustomAdapter(playStoreContent, isType)
        when(isType){
            0 -> {
                viewPager.adapter = customAdapter
            }
            1 -> {
                recyclerView.adapter = customAdapter
                //pagersnaphelper
                pagerSnapHelper.attachToRecyclerView(recyclerView)
            }
            2 -> {
                recyclerView.adapter = customAdapter
                pagerSnapHelper.attachToRecyclerView(recyclerView)
            }
        }

    }

}