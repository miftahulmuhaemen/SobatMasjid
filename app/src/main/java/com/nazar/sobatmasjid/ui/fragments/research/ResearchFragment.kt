package com.nazar.sobatmasjid.ui.fragments.research

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nazar.sobatmasjid.databinding.FragmentResearchBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.base.BaseBottomTabFragment
import com.nazar.sobatmasjid.ui.fragments.location.LocationViewModel
import com.nazar.sobatmasjid.ui.pager.ResearchPager
import com.nazar.sobatmasjid.utils.extensions.afterTextChanged
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory

class ResearchFragment : BaseBottomTabFragment() {

    private lateinit var binding: FragmentResearchBinding
    private lateinit var researchViewModel: ResearchViewModel
    private lateinit var locationViewModel: LocationViewModel
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

        val factory = ViewModelFactory.getInstance(requireContext())
        researchViewModel = ViewModelProvider(this, factory)[ResearchViewModel::class.java]
        locationViewModel = ViewModelProvider(requireActivity(), factory)[LocationViewModel::class.java]
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