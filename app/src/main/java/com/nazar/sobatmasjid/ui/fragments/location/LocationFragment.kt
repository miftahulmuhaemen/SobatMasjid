package com.nazar.sobatmasjid.ui.fragments.location

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentLocationBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.adapters.LocationAdapter
import com.nazar.sobatmasjid.ui.base.BaseBottomSheetFragment
import com.nazar.sobatmasjid.utils.extensions.afterTextChanged
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory
import com.nazar.sobatmasjid.vo.Status

class LocationFragment : BaseBottomSheetFragment() {

    private lateinit var binding: FragmentLocationBinding
    private lateinit var viewModel: LocationViewModel
    private lateinit var locationAdapter: LocationAdapter
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }

    companion object {
        const val KEY_ON_LOCATION_DIALOG_DISMISS = "DISMISS_LOCATION_DIALOG"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val bottomSheet =
                (it as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = false
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[LocationViewModel::class.java]

        locationAdapter = LocationAdapter {
            preferences.setCity(it)
            /**
             *
             * SHAAARED MODEL KENAPA TIDAK DIPAKAI
             * **/
            findNavController().previousBackStackEntry?.savedStateHandle?.set(KEY_ON_LOCATION_DIALOG_DISMISS, it.name)
            dismiss()
        }
        with(binding.rvLocation) {
            layoutManager = LinearLayoutManager(context)
            adapter = locationAdapter
        }
        loadData("")

        binding.searchView.afterTextChanged { loadData(it) }
        binding.searchView.setOnCloseListener {
            loadData("")
            false
        }
        binding.btnBack.setOnClickListener { dismiss() }
    }

    private fun loadData(query: String) {
        viewModel.getCities(query).observe(viewLifecycleOwner, { cities ->
            if (cities != null) {
                when (cities.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        locationAdapter.submitList(cities.data)
                        locationAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

}