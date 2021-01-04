package com.nazar.sobatmasjid.ui.fragments.research

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nazar.sobatmasjid.databinding.FragmentResearchBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.base.BaseBottomTabFragment
import com.nazar.sobatmasjid.ui.fragments.announcement.AnnouncementViewModel
import com.nazar.sobatmasjid.ui.fragments.location.LocationViewModel
import com.nazar.sobatmasjid.ui.pager.ResearchPager
import com.nazar.sobatmasjid.utils.extensions.afterTextChanged
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResearchFragment : BaseBottomTabFragment() {

    private lateinit var binding: FragmentResearchBinding
    private val researchViewModel by sharedViewModel<ResearchViewModel>()
    private val locationViewModel by sharedViewModel<LocationViewModel>()
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationViewModel.location.observe(viewLifecycleOwner, {
            preferences.setCity(it)
            binding.btnCurrentLocation.text = preferences.nameCity
        })

        val sectionPager = ResearchPager(requireContext(), childFragmentManager)
        binding.pagerResearch.adapter = sectionPager
        binding.tabsResearch.setupWithViewPager(binding.pagerResearch)
        binding.svResearch.afterTextChanged { researchViewModel.setQuery(it) }
        binding.svResearch.setOnCloseListener {
            researchViewModel.setQuery("")
            true
        }
        binding.btnCurrentLocation.text = preferences.nameCity
        binding.btnCurrentLocation.setOnClickListener {
            navigateWithAction(ResearchFragmentDirections.actionResearchFragmentToLocationFragment())
        }
    }

}