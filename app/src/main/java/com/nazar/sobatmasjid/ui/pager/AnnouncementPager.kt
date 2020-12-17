package com.nazar.sobatmasjid.ui.pager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.ui.fragments.announcement.list.AnnouncementListFragment
import com.nazar.sobatmasjid.ui.fragments.research.list.ResearchListFragment
import java.util.*

class AnnouncementPager(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)   {

    private val tabTitles: Array<String> = context.resources.getStringArray(R.array.announcement_category)

    companion object {
        const val KEY_TYPE = "KEY_TYPE"
    }

    override fun getItem(position: Int): Fragment {
        val fragment = AnnouncementListFragment()
        val bundle = Bundle()
            bundle.putString(KEY_TYPE, tabTitles[position])
        fragment.arguments = bundle
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence = tabTitles[position]

    override fun getCount(): Int = tabTitles.size
}