package com.nazar.sobatmasjid.ui.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentHomeBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.adapters.AnnouncementAdapter
import com.nazar.sobatmasjid.ui.adapters.MosqueFitAdapter
import com.nazar.sobatmasjid.ui.adapters.MosqueRecommendationAdapter
import com.nazar.sobatmasjid.ui.adapters.ResearchAdapter
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory
import com.nazar.sobatmasjid.vo.Status

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }
    private val navHostController by lazy {
        (requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment).navController
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
            setupFollowRecommendation()
        else {
            setupHome()
        }

    }

    private fun setupFollowRecommendation(){
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


    private fun setupHome(){

        binding.layoutMosqueRecommendation.visibility = View.GONE
        binding.layoutHome.visibility = View.VISIBLE

        val mosqueFitAdapter = MosqueFitAdapter()
        with(binding.rvMosque){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = mosqueFitAdapter
        }
        viewModel.getMosques(
            preferences.latitude,
            preferences.longitude,
            preferences.idCity,
            "Al-Istiqomah"
        ).observe(viewLifecycleOwner, { mosques ->
            if(mosques != null){
                when(mosques.status){
                    Status.LOADING -> {}
                    Status.SUCCESS -> {
                        Log.d("LOG", mosques.toString())
                        mosqueFitAdapter.submitList(mosques.data)
                        mosqueFitAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        val researchAdapter = ResearchAdapter()
        with(binding.rvResearch){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = researchAdapter
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

        val announcementAdapter = AnnouncementAdapter()
        with(binding.rvAnnouncement){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = announcementAdapter
        }
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
                        researchAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

//        viewModel.getFridayPrayers(
//            preferences.idCity,
//            preferences.idUser,
//            preferences.latitude,
//            preferences.longitude
//        ).observe(viewLifecycleOwner, { fridayPrayers ->
//            if(fridayPrayers != null){
//                when(fridayPrayers.status){
//
//                }
//            }
//        })
    }
}