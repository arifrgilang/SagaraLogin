package com.arifrgilang.sagaralogin.util

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomViewPager(ctx: Context, attrs: AttributeSet) : ViewPager(ctx, attrs){
    private var swipeEnabled = false

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return when(swipeEnabled){
            true -> super.onTouchEvent(ev)
            false -> false
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return when(swipeEnabled){
            true -> super.onInterceptTouchEvent(ev)
            false -> false
        }
    }

    fun setSwipePagingEnabled(condition: Boolean){
        this.swipeEnabled = condition
    }
}