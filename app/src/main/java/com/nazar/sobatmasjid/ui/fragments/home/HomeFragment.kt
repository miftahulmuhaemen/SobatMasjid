
package com.nazar.sobatmasjid.ui.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentHomeBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.adapters.*
import com.nazar.sobatmasjid.ui.fragments.location.LocationFragment.Companion.KEY_ON_LOCATION_DIALOG_DISMISS
import com.nazar.sobatmasjid.utils.extensions.setGone
import com.nazar.sobatmasjid.utils.extensions.setVisible
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory
import com.nazar.sobatmasjid.vo.Status

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var mosqueFitAdapter: MosqueFitAdapter
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        if(preferences.numberFollow == 0)
            setupDataByRecommendation()
        else {
            binding.layoutMosqueRecommendation.setGone()
            binding.layoutHome.setVisible()
            setupDataByUser()
            setupDataByLocation()
            afterLocationDialogDismiss()
        }

        binding.btnCurrentLocation.text = preferences.nameCity
        binding.btnMosqueList.setOnClickListener { findNavController().navigate(R.id.mosqueFragment) }
        binding.btnCurrentLocation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_locationFragment)
        }
    }

    private fun afterLocationDialogDismiss(){
        val navBackStackEntry = findNavController().getBackStackEntry(R.id.homeFragment)
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME
                && navBackStackEntry.savedStateHandle.contains(KEY_ON_LOCATION_DIALOG_DISMISS)) {
                val result = navBackStackEntry.savedStateHandle.get<String>(KEY_ON_LOCATION_DIALOG_DISMISS)
                if(!result.isNullOrBlank()){
                    setupDataByLocation()
                    binding.btnCurrentLocation.text = preferences.nameCity
                }
            }
        }
        navBackStackEntry.lifecycle.addObserver(observer)
        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                navBackStackEntry.lifecycle.removeObserver(observer)
            }
        })
    }

    private fun setupDataByRecommendation(){
        val mosqueRecommendationAdapter = MosqueRecommendationAdapter { position ->
            viewModel.followMosque(preferences.idUser, position)
            preferences.numberFollow = 1
        }
        with(binding.rvMosqueGreeting){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = mosqueRecommendationAdapter
        }
        viewModel.getMosqueRecommendations(
            preferences.latitude,
            preferences.longitude
        ).observe(viewLifecycleOwner, { mosques ->
            if(mosques != null){
                when(mosques.status){
                    Status.LOADING -> {}
                    Status.SUCCESS -> {
                        mosqueRecommendationAdapter.submitList(mosques.data)
                        mosqueRecommendationAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setupDataByLocation(){
        mosqueFitAdapter = MosqueFitAdapter()
        val mosqueType = resources.getStringArray(R.array.mosque_type).toList()
        val mosqueClassification = resources.getStringArray(R.array.mosque_classification).toList()
        with(binding.rvMosque){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mosqueFitAdapter
        }
        viewModel.getMosques(
            preferences.latitude,
            preferences.longitude,
            preferences.idCity,
            "",
            mosqueType,
            mosqueClassification
        ).observe(viewLifecycleOwner, { mosques ->
            if(mosques != null){
                when(mosques.status){
                    Status.LOADING -> {}
                    Status.SUCCESS -> {
                        mosqueFitAdapter.submitList(mosques.data)
                        mosqueFitAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setupDataByUser(){
        val researchAdapter = ResearchAdapter()
        with(binding.rvResearch){
            layoutManager = LinearLayoutManager(context)
            adapter = researchAdapter
        }
        val announcementAdapter = AnnouncementAdapter()
        with(binding.rvAnnouncement){
            layoutManager = LinearLayoutManager(context)
            adapter = announcementAdapter
        }
        val fridayPrayerAdapter = FridayPrayerAdapter()
        with(binding.rvFridayPrayer){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = fridayPrayerAdapter
        }
        viewModel.getResearches(
            preferences.idUser,
            preferences.latitude,
            preferences.longitude
        ).observe(viewLifecycleOwner, { researches ->
            if(researches != null){
                when(researches.status){
                    Status.LOADING -> {}
                    Status.SUCCESS -> {
                        researchAdapter.submitList(researches.data)
                        researchAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        viewModel.getAnnouncements(
            preferences.idUser,
            preferences.latitude,
            preferences.longitude
        ).observe(viewLifecycleOwner, { announcements ->
            if(announcements != null){
                when(announcements.status){
                    Status.LOADING -> {}
                    Status.SUCCESS -> {
                        announcementAdapter.submitList(announcements.data)
                        announcementAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        viewModel.getFridayPrayers(
            preferences.idUser,
            preferences.latitude,
            preferences.longitude
        ).observe(viewLifecycleOwner, { fridayPrayers ->
            if(fridayPrayers != null){
                when(fridayPrayers.status){
                    Status.LOADING -> {}
                    Status.SUCCESS -> {
                        fridayPrayerAdapter.submitList(fridayPrayers.data)
                        fridayPrayerAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
//                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

}