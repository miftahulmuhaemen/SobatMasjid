package com.nazar.sobatmasjid.ui.pager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.ui.fragments.mosque.list.MosqueListFragment

class MosquePager(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)   {

    private val tabTitles: Array<String> = context.resources.getStringArray(R.array.mosque_type)

    companion object {
        const val KEY_TYPE = "KEY_TYPE"
    }

    override fun getItem(position: Int): Fragment {
        val fragment = MosqueListFragment()
        val bundle = Bundle()
            bundle.putString(KEY_TYPE, tabTitles[position])
        fragment.arguments = bundle
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence = tabTitles[position]

    override fun getCount(): Int = tabTitles.size
}