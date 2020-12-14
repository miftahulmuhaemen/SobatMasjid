package com.nazar.sobatmasjid.ui.fragments.mosque

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentMosqueBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.base.BaseBottomTabFragment
import com.nazar.sobatmasjid.ui.fragments.location.LocationViewModel
import com.nazar.sobatmasjid.utils.extensions.afterTextChanged
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory

class MosqueFragment : BaseBottomTabFragment() {

    private lateinit var binding: FragmentMosqueBinding
    private lateinit var mosqueViewModel: MosqueViewModel
    private lateinit var locationViewModel: LocationViewModel
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMosqueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = ViewModelFactory.getInstance(requireContext())
        mosqueViewModel = ViewModelProvider(findNavController().currentBackStackEntry?.viewModelStore!!, factory)[MosqueViewModel::class.java]
        locationViewModel = ViewModelProvider(requireActivity(), factory)[LocationViewModel::class.java]

        mosqueViewModel.classification.postValue(resources.getStringArray(R.array.mosque_classification).toList())
        locationViewModel.location.observe(viewLifecycleOwner, {
            preferences.setCity(it)
            binding.btnCurrentLocation.text = preferences.nameCity
            mosqueViewModel.location.value = it.name
        })

        val sectionsPagerAdapter = MosquePagerAdapter(requireContext(), childFragmentManager)
        binding.pagerMosque.adapter = sectionsPagerAdapter
        binding.tabsMosque.setupWithViewPager(binding.pagerMosque)
        binding.svMosque.afterTextChanged { mosqueViewModel.query.value = it }
        binding.svMosque.setOnCloseListener {
            mosqueViewModel.query.value = ""
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