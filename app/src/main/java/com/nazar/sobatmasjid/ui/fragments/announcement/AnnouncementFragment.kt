package com.nazar.sobatmasjid.ui.fragments.announcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nazar.sobatmasjid.databinding.FragmentAnnouncementBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.base.BaseBottomTabFragment
import com.nazar.sobatmasjid.ui.fragments.location.LocationViewModel
import com.nazar.sobatmasjid.ui.fragments.research.ResearchFragmentDirections
import com.nazar.sobatmasjid.ui.pager.AnnouncementPager
import com.nazar.sobatmasjid.utils.extensions.afterTextChanged
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnnouncementFragment : BaseBottomTabFragment() {

    private lateinit var binding: FragmentAnnouncementBinding
    private val announcementViewModel by sharedViewModel<AnnouncementViewModel>()
    private val locationViewModel by sharedViewModel<LocationViewModel>()
    private val preferences: Preferences by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnnouncementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationViewModel.location.observe(viewLifecycleOwner, {
            preferences.setCity(it)
            binding.btnCurrentLocation.text = preferences.nameCity
        })

        val sectionPager = AnnouncementPager(requireContext(), childFragmentManager)
        binding.pagerAnnouncement.adapter = sectionPager
        binding.tabsAnnouncement.setupWithViewPager(binding.pagerAnnouncement)
        binding.svAnnouncement.afterTextChanged { announcementViewModel.setQuery(it) }
        binding.svAnnouncement.setOnCloseListener {
            announcementViewModel.setQuery("")
            true
        }
        binding.btnCurrentLocation.text = preferences.nameCity
        binding.btnCurrentLocation.setOnClickListener {
            navigateWithAction(ResearchFragmentDirections.actionResearchFragmentToLocationFragment())
        }
    }

}