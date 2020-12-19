package com.nazar.sobatmasjid.ui.fragments.announcement.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentRecyclerviewBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.adapters.AnnouncementAdapter
import com.nazar.sobatmasjid.ui.fragments.announcement.AnnouncementFragmentDirections
import com.nazar.sobatmasjid.ui.fragments.announcement.AnnouncementViewModel
import com.nazar.sobatmasjid.ui.fragments.location.LocationViewModel
import com.nazar.sobatmasjid.ui.pager.AnnouncementPager.Companion.KEY_TYPE
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory
import com.nazar.sobatmasjid.vo.Status

class AnnouncementListFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerviewBinding
    private lateinit var announcementListViewModel: AnnouncementListViewModel
    private lateinit var announcementViewModel: AnnouncementViewModel
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var announcementAdapter: AnnouncementAdapter
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }
    private var query: String = ""
    private var announcementCategory: List<String> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        announcementListViewModel =
            ViewModelProvider(this, factory)[AnnouncementListViewModel::class.java]
        announcementViewModel =
            ViewModelProvider(requireParentFragment(), factory)[AnnouncementViewModel::class.java]
        locationViewModel =
            ViewModelProvider(requireActivity(), factory)[LocationViewModel::class.java]

        val type = requireArguments().getString(KEY_TYPE)
        if (!type.isNullOrBlank())
            announcementCategory = when (type) {
                getString(R.string.all) -> {
                    requireContext().resources.getStringArray(R.array.announcement_category).toList()
                }
                else -> {
                    listOf(type)
                }
            }

        announcementAdapter = AnnouncementAdapter {
            findNavController().navigate(AnnouncementFragmentDirections.actionAnnouncementFragmentToAnnouncementDetailFragment(
                it
            ))
        }
        with(binding.recyclerview) {
            layoutManager = LinearLayoutManager(context)
            adapter = announcementAdapter
        }

        setupObserver()
    }

    private fun setupObserver() {
        locationViewModel.location.observe(viewLifecycleOwner, {
            if (it != null)
                loadData(query)
        })
        announcementViewModel.query.observe(viewLifecycleOwner, {
            query = it
            loadData(it)
        })
        loadData(query)
    }

    private fun loadData(query: String) {
        announcementListViewModel.getAnnouncements(
            preferences.latitude,
            preferences.longitude,
            preferences.idCity,
            query,
            announcementCategory
        ).observe(viewLifecycleOwner, { announcements ->
            if (announcements != null) {
                when (announcements.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        Log.d("LOGI", query)
                        Log.d("LOGI", announcements.data.toString())
                        Log.d("LOGI", announcementCategory.toString())
                        announcementAdapter.submitList(announcements.data)
                        announcementAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, getString(R.string.notification_warning), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}