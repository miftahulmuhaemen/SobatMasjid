package com.nazar.sobatmasjid.ui.fragments.mosque.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentRecyclerviewBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.adapters.MosqueWideAdapter
import com.nazar.sobatmasjid.ui.fragments.location.LocationViewModel
import com.nazar.sobatmasjid.ui.fragments.mosque.MosqueViewModel
import com.nazar.sobatmasjid.ui.pager.MosquePager.Companion.KEY_TYPE
import com.nazar.sobatmasjid.vo.Status
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MosqueListFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerviewBinding
    private val mosqueListViewModel by viewModel<MosqueListViewModel>()
    private val mosqueViewModel by sharedViewModel<MosqueViewModel>()
    private val locationViewModel by sharedViewModel<LocationViewModel>()
    private lateinit var mosqueAdapter: MosqueWideAdapter
    private val preferences: Preferences by inject()
    private var query: String = ""
    private var mosqueType: List<String> = listOf()
    private var mosqueClassification: List<String> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type = requireArguments().getString(KEY_TYPE)
        if (!type.isNullOrBlank())
            mosqueType = when (type) {
                getString(R.string.all) -> {
                    requireContext().resources.getStringArray(R.array.mosque_type).toList()
                }
                else -> {
                    listOf(type)
                }
            }

        mosqueAdapter = MosqueWideAdapter()
        with(binding.recyclerview) {
            layoutManager = LinearLayoutManager(context)
            adapter = mosqueAdapter
        }

        setupObserver()
    }

    private fun setupObserver() {
        locationViewModel.location.observe(viewLifecycleOwner, {
            if (it != null) {
                preferences.setCity(it)
                loadData(query, mosqueClassification)
            }
        })
        mosqueViewModel.query.observe(viewLifecycleOwner, {
            query = it
            if (!it.isNullOrEmpty())
                loadData(it, mosqueClassification)
            else if (!mosqueClassification.isNullOrEmpty())
                loadData("", mosqueClassification)
        })
        mosqueViewModel.classification.observe(viewLifecycleOwner, {
            mosqueClassification = it
            if (!it.isNullOrEmpty())
                loadData(query, mosqueClassification)
        })
    }

    private fun loadData(query: String, classification: List<String>) {
        mosqueListViewModel.getMosques(
            preferences.latitude,
            preferences.longitude,
            preferences.idCity,
            query,
            mosqueType,
            classification
        ).observe(viewLifecycleOwner, { mosques ->
            if (mosques != null) {
                when (mosques.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        mosqueAdapter.submitList(mosques.data)
                        mosqueAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, getString(R.string.notification_warning), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}