package com.nazar.sobatmasjid.ui.fragments.adhan

import android.app.Dialog
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentAdhanBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.base.BaseBottomSheetFragment
import com.nazar.sobatmasjid.ui.fragments.location.LocationViewModel
import com.nazar.sobatmasjid.vo.Status
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.sql.Date
import java.time.LocalDate

class AdhanFragment : BaseBottomSheetFragment() {

    private lateinit var binding: FragmentAdhanBinding
    private val adhanViewModel: AdhanViewModel by viewModel()
    private val locationViewModel by sharedViewModel<LocationViewModel>()
    private val preferences: Preferences by inject()

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
    ): View? {
        binding = FragmentAdhanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationViewModel.location.observe(viewLifecycleOwner, {
            loadData()
        })

        binding.btnCurrentLocation.text = preferences.nameCity
        binding.btnCurrentLocation.setOnClickListener {
            findNavController().navigate(AdhanFragmentDirections.actionAdhanFragmentToLocationFragment())
        }
        binding.btnBack.setOnClickListener { dismiss() }
        loadData()
    }

    private fun loadData(){
        val date = Date.valueOf(LocalDate.now().toString())
        adhanViewModel.getSholatTimes(preferences.apiCode, date).observe(
            viewLifecycleOwner,
            { sholatTimes ->
                if (sholatTimes != null) {
                    when (sholatTimes.status) {
                        Status.LOADING -> { }
                        Status.SUCCESS -> {
                            val adhan = sholatTimes.data
                            if (adhan != null) {
                                binding.tvDate.text = adhan.date
                                binding.tvImsakTime.text = adhan.imsak
                                binding.tvSubuhTime.text = adhan.shubuh
                                binding.tvDzuhurTime.text = adhan.dzuhur
                                binding.tvAsharTime.text = adhan.ashar
                                binding.tvMaghribTime.text = adhan.maghrib
                                binding.tvIsyaTime.text = adhan.isya
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(
                                context,
                                getString(R.string.notification_warning),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
    }

}