package com.nazar.sobatmasjid.ui.fragments.announcement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.ui.base.BaseBottomTabFragment

class AnnouncementFragment : BaseBottomTabFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_announcement_list, container, false)
    }

}