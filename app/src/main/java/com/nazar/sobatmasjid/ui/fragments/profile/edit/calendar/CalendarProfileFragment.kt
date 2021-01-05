package com.nazar.sobatmasjid.ui.fragments.profile.edit.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nazar.sobatmasjid.databinding.FragmentCalendarBinding
import com.nazar.sobatmasjid.ui.fragments.profile.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CalendarProfileFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCalendarBinding
    private val profileViewModel by sharedViewModel<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnClose.setOnClickListener { dismiss() }
        binding.btnActionChange.setOnClickListener {
            val year = binding.datePicker.year
            val month = binding.datePicker.month
            val day = binding.datePicker.dayOfMonth
            val calendar = "$year-$month-$day"
            profileViewModel.setBornDate(calendar)
            dismiss()
        }
    }
}