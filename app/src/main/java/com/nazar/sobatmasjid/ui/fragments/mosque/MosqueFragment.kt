package com.nazar.sobatmasjid.ui.fragments.mosque

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentMosqueBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.fragments.location.LocationFragment.Companion.KEY_ON_LOCATION_DIALOG_DISMISS
import com.nazar.sobatmasjid.utils.extensions.afterTextChanged
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory

class MosqueFragment : Fragment() {

    private lateinit var binding: FragmentMosqueBinding
    private lateinit var viewModel: MosqueViewModel
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
        viewModel = ViewModelProvider(this, factory)[MosqueViewModel::class.java]
        viewModel.classification.value = resources.getStringArray(R.array.mosque_classification).toList()

        val sectionsPagerAdapter = MosquePagerAdapter(requireContext(), childFragmentManager)
        binding.pagerMosque.adapter = sectionsPagerAdapter
        binding.tabsMosque.setupWithViewPager(binding.pagerMosque)
        binding.svMosque.afterTextChanged { viewModel.query.value = it }
        binding.svMosque.setOnCloseListener {
            viewModel.query.value = ""
            true
        }
        binding.btnCurrentLocation.text = preferences.nameCity
        binding.btnCurrentLocation.setOnClickListener {
            findNavController().navigate(R.id.action_mosqueFragment_to_locationFragment)
        }

        afterLocationDialogDismiss()
    }

    private fun afterLocationDialogDismiss(){
        val navBackStackEntry = findNavController().getBackStackEntry(R.id.mosqueFragment)
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME
                && navBackStackEntry.savedStateHandle.contains(KEY_ON_LOCATION_DIALOG_DISMISS)) {
                val result = navBackStackEntry.savedStateHandle.get<String>(KEY_ON_LOCATION_DIALOG_DISMISS)
                if(!result.isNullOrBlank())
                    viewModel.location.value = result
                    binding.btnCurrentLocation.text = preferences.nameCity
            }
        }
        navBackStackEntry.lifecycle.addObserver(observer)
        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                navBackStackEntry.lifecycle.removeObserver(observer)
            }
        })
    }

}