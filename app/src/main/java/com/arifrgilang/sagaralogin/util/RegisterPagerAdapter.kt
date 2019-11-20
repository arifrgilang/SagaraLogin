package com.arifrgilang.sagaralogin.util

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.arifrgilang.sagaralogin.fragments.PageOneFragment
import com.arifrgilang.sagaralogin.fragments.PageTwoFragment

class RegisterPagerAdapter(private val ctx: Context, fm: FragmentManager) : FragmentPagerAdapter(fm){
    private val fragments = listOf(
        PageOneFragment(),
        PageTwoFragment()
    )
    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

}