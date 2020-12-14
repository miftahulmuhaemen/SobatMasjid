package com.nazar.sobatmasjid.ui.fragments.mosque.list

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
import com.nazar.sobatmasjid.ui.adapters.MosqueWideAdapter
import com.nazar.sobatmasjid.ui.fragments.mosque.MosquePagerAdapter.Companion.KEY_TYPE
import com.nazar.sobatmasjid.ui.fragments.mosque.MosqueViewModel
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory
import com.nazar.sobatmasjid.vo.Status

class MosqueListFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerviewBinding
    private lateinit var mosqueListViewModel: MosqueListViewModel
    private lateinit var mosqueViewModel: MosqueViewModel
    private lateinit var mosqueAdapter: MosqueWideAdapter
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }
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

        val factory = ViewModelFactory.getInstance(requireContext())
        mosqueListViewModel = ViewModelProvider(this, factory)[MosqueListViewModel::class.java]
        mosqueViewModel = ViewModelProvider(findNavController().currentBackStackEntry?.viewModelStore!!, factory)[MosqueViewModel::class.java]

        val type = requireArguments().getString(KEY_TYPE)
        mosqueType = if (!type.isNullOrBlank())
            listOf(type)
        else
            resources.getStringArray(R.array.mosque_type).toList()

        mosqueAdapter = MosqueWideAdapter()
        with(binding.recyclerview) {
            layoutManager = LinearLayoutManager(context)
            adapter = mosqueAdapter
        }

        mosqueViewModel.location.observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty())
                loadData(query, mosqueClassification)
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
            if(!it.isNullOrEmpty())
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
                    Status.LOADING -> {                    }
                    Status.SUCCESS -> {
                        mosqueAdapter.submitList(mosques.data)
                        mosqueAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}