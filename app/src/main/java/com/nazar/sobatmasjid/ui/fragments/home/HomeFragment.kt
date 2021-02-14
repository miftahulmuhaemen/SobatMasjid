package com.nazar.sobatmasjid.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentHomeBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.adapters.*
import com.nazar.sobatmasjid.ui.base.BaseBottomTabFragment
import com.nazar.sobatmasjid.ui.fragments.location.LocationViewModel
import com.nazar.sobatmasjid.utils.extensions.setGone
import com.nazar.sobatmasjid.utils.extensions.setVisible
import com.nazar.sobatmasjid.vo.Status
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseBottomTabFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val locationViewModel by sharedViewModel<LocationViewModel>()
    private lateinit var mosqueFitAdapter: MosqueFitAdapter
    private lateinit var mosqueRecommendationAdapter: MosqueRecommendationAdapter
    private lateinit var researchAdapter: ResearchAdapter
    private lateinit var announcementAdapter: AnnouncementAdapter
    private lateinit var fridayPrayerAdapter: FridayPrayerAdapter
    private val preferences: Preferences by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (preferences.numberFollow == 0){
            setupDataByRecommendation()
        }
        else {
            binding.layoutMosqueRecommendation.setGone()
            binding.layoutHome.setVisible()
            setupAdapters()
            setupDataByUser()
            setupDataByLocation()
            locationViewModel.location.observe(viewLifecycleOwner, {
                if (it != null) {
                    preferences.setCity(it)
                    binding.btnCurrentLocation.text = preferences.nameCity
                    setupDataByLocation()
                }
            })
        }

        binding.btnCurrentLocation.text = preferences.nameCity
        binding.btnMosqueList.setOnClickListener { navigate(R.id.mosqueFragment) }
        binding.btnAnnouncement.setOnClickListener { navigate(R.id.announcementFragment) }
        binding.btnResearchList.setOnClickListener { navigate(R.id.researchFragment) }
        binding.btnCurrentLocation.setOnClickListener {
            navigateWithAction(HomeFragmentDirections.actionHomeFragmentToLocationFragment())
        }
        binding.btnAdhan.setOnClickListener {
            navigateWithAction(HomeFragmentDirections.actionHomeFragmentToAdhanFragment())
        }
    }

    private fun setupAdapters() {
        mosqueFitAdapter = MosqueFitAdapter()
        with(binding.rvMosque) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mosqueFitAdapter
        }
        researchAdapter = ResearchAdapter {
            navigateWithAction(HomeFragmentDirections.actionHomeFragmentToResearchDetail(it))
        }
        with(binding.rvResearch) {
            layoutManager = LinearLayoutManager(context)
            adapter = researchAdapter
        }
        announcementAdapter = AnnouncementAdapter {
            navigateWithAction(HomeFragmentDirections.actionHomeFragmentToAnnouncementDetailFragment(it))
        }
        with(binding.rvAnnouncement) {
            layoutManager = LinearLayoutManager(context)
            adapter = announcementAdapter
        }
        fridayPrayerAdapter = FridayPrayerAdapter()
        with(binding.rvFridayPrayer) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = fridayPrayerAdapter
        }
    }

    private fun setupDataByRecommendation() {
        mosqueRecommendationAdapter = MosqueRecommendationAdapter { position ->
            viewModel.followMosque(preferences.idUser, position)
            preferences.numberFollow = 1
        }
        with(binding.rvMosqueGreeting) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = mosqueRecommendationAdapter
        }
        viewModel.getMosqueRecommendations(
            preferences.latitude,
            preferences.longitude
        ).observe(viewLifecycleOwner, { mosques ->
            if (mosques != null) {
                when (mosques.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        mosqueRecommendationAdapter.submitList(mosques.data)
                        mosqueRecommendationAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, getString(R.string.notification_warning), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setupDataByLocation() {
        val mosqueType = resources.getStringArray(R.array.mosque_type).toList()
        val mosqueClassification = resources.getStringArray(R.array.mosque_classification).toList()
        viewModel.getMosques(
            preferences.latitude,
            preferences.longitude,
            preferences.idCity,
            "",
            mosqueType,
            mosqueClassification
        ).observe(viewLifecycleOwner, { mosques ->
            if (mosques != null) {
                when (mosques.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        mosqueFitAdapter.submitList(mosques.data)
                        mosqueFitAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, getString(R.string.notification_warning), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setupDataByUser() {
        viewModel.getResearches(
            preferences.idUser,
            preferences.latitude,
            preferences.longitude
        ).observe(viewLifecycleOwner, { researches ->
            if (researches != null) {
                when (researches.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        researchAdapter.submitList(researches.data)
                        researchAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, getString(R.string.notification_warning), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        viewModel.getAnnouncements(
            preferences.idUser,
            preferences.latitude,
            preferences.longitude
        ).observe(viewLifecycleOwner, { announcements ->
            if (announcements != null) {
                when (announcements.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        announcementAdapter.submitList(announcements.data)
                        announcementAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, getString(R.string.notification_warning), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        viewModel.getFridayPrayers(
            preferences.idUser,
            preferences.latitude,
            preferences.longitude
        ).observe(viewLifecycleOwner, { fridayPrayers ->
            if (fridayPrayers != null) {
                when (fridayPrayers.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        fridayPrayerAdapter.submitList(fridayPrayers.data)
                        fridayPrayerAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, getString(R.string.notification_warning), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

}