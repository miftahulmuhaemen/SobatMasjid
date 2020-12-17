package com.nazar.sobatmasjid.ui.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nazar.sobatmasjid.ui.fragments.mosque.detail.announcement.MosqueDetailAnnouncementFragment
import com.nazar.sobatmasjid.ui.fragments.mosque.detail.fridayPrayer.MosqueDetailFridayPrayerFragment
import com.nazar.sobatmasjid.ui.fragments.mosque.detail.profile.MosqueDetailProfileFragment
import com.nazar.sobatmasjid.ui.fragments.mosque.detail.research.MosqueDetailResearchFragment

class MosqueDetailPager(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)   {

    companion object {
        private val TAB_TITLES = arrayListOf("Profil","Kajian","Pengumuman", "Jum'atan")
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MosqueDetailProfileFragment()
            1 -> MosqueDetailResearchFragment()
            2 -> MosqueDetailAnnouncementFragment()
            3 -> MosqueDetailFridayPrayerFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = TAB_TITLES[position]

    override fun getCount(): Int = TAB_TITLES.size
}