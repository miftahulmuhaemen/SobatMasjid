package com.nazar.sobatmasjid.ui.pager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.ui.fragments.mosque.list.MosqueListFragment

class MosquePager(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)   {

    companion object {
        private val TAB_TITLES = arrayListOf("Semua","Masjid","Musholla")
        const val KEY_TYPE = "KEY_TYPE"
    }

    override fun getItem(position: Int): Fragment {
        val fragment = when (position) {
            0 -> MosqueListFragment()
            1 -> MosqueListFragment()
            2 -> MosqueListFragment()
            else -> Fragment()
        }

        val types = context.resources.getStringArray(R.array.mosque_type)
        val bundle = Bundle()
            bundle.putString(
                KEY_TYPE, when(position) {
            1 -> types[0]
            2 -> types[1]
            else -> null
        })

        fragment.arguments = bundle
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence = TAB_TITLES[position]

    override fun getCount(): Int = TAB_TITLES.size
}