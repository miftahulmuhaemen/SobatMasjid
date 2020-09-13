package com.nazar.sobatmasjid.ui.introduction

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nazar.sobatmasjid.R


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class IntroPagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> Fragment(R.layout.item_introduction_one)
        1 -> Fragment(R.layout.item_introduction_two)
        2 -> Fragment(R.layout.item_introduction_three)
        else -> Fragment()
    }
    override fun getItemCount(): Int = 3

}