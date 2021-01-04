package com.nazar.sobatmasjid.ui.fragments.research.list

import android.os.Bundle
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
import com.nazar.sobatmasjid.ui.adapters.ResearchAdapter
import com.nazar.sobatmasjid.ui.fragments.location.LocationViewModel
import com.nazar.sobatmasjid.ui.fragments.research.ResearchFragmentDirections
import com.nazar.sobatmasjid.ui.fragments.research.ResearchViewModel
import com.nazar.sobatmasjid.ui.pager.ResearchPager.Companion.KEY_TYPE
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.nazar.sobatmasjid.vo.Status

class ResearchListFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerviewBinding
    private val researchListViewModel: ResearchListViewModel by viewModel()
    private val researchViewModel by sharedViewModel<ResearchViewModel>()
    private val locationViewModel by sharedViewModel<LocationViewModel>()
    private lateinit var researchAdapter: ResearchAdapter
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }
    private var query: String = ""
    private var researchType: List<String> = listOf()

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
            researchType = when (type) {
                getString(R.string.all) -> {
                    requireContext().resources.getStringArray(R.array.research_type).toList()
                }
                else -> {
                    listOf(type)
                }
            }

        researchAdapter = ResearchAdapter {
            findNavController().navigate(ResearchFragmentDirections.actionResearchFragmentToResearchDetail(it))
        }
        with(binding.recyclerview) {
            layoutManager = LinearLayoutManager(context)
            adapter = researchAdapter
        }

        setupObserver()
    }

    private fun setupObserver() {
        locationViewModel.location.observe(viewLifecycleOwner, {
            if (it != null)
                loadData(query)
        })
        researchViewModel.query.observe(viewLifecycleOwner, {
            query = it
            loadData(it)
        })
        loadData(query)
    }

    private fun loadData(query: String) {
        researchListViewModel.getResearches(
            preferences.latitude,
            preferences.longitude,
            preferences.idCity,
            query,
            researchType
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
    }
}