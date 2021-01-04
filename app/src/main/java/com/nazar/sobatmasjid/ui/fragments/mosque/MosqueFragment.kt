package com.nazar.sobatmasjid.ui.fragments.mosque

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentMosqueBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.base.BaseBottomTabFragment
import com.nazar.sobatmasjid.ui.fragments.location.LocationViewModel
import com.nazar.sobatmasjid.ui.pager.MosquePager
import com.nazar.sobatmasjid.utils.extensions.afterTextChanged
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MosqueFragment : BaseBottomTabFragment() {

    private lateinit var binding: FragmentMosqueBinding
    private val mosqueViewModel by sharedViewModel<MosqueViewModel>()
    private val locationViewModel by sharedViewModel<LocationViewModel>()
    private val preferences: Preferences by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMosqueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationViewModel.location.observe(viewLifecycleOwner, {
            preferences.setCity(it)
            binding.btnCurrentLocation.text = preferences.nameCity
        })
        val sectionsPagerAdapter = MosquePager(requireContext(), childFragmentManager)
        val classification = resources.getStringArray(R.array.mosque_classification).toList()
        mosqueViewModel.setClassification(classification)
        binding.pagerMosque.adapter = sectionsPagerAdapter
        binding.tabsMosque.setupWithViewPager(binding.pagerMosque)
        binding.svMosque.afterTextChanged { mosqueViewModel.setQuery(it) }
        binding.svMosque.setOnCloseListener {
            mosqueViewModel.setQuery("")
            true
        }
        binding.btnCurrentLocation.text = preferences.nameCity
        binding.btnCurrentLocation.setOnClickListener {
            navigateWithAction(MosqueFragmentDirections.actionMosqueFragmentToLocationFragment())
        }
        binding.btnFilter.setOnClickListener {
            navigateWithAction(MosqueFragmentDirections.actionMosqueFragmentToMosqueFilterFragment())
        }
    }

}