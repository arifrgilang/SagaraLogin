package com.arifrgilang.sagaralogin

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class RegisterPagerAdapter(private val ctx: Context, fm: FragmentManager) : FragmentPagerAdapter(fm){
    private val fragments = listOf(
        PageOneFragment(),
        PageTwoFragment()
    )
    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

}