package com.nazar.sobatmasjid.ui.fragments.profile.edit.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nazar.sobatmasjid.databinding.FragmentCalendarBinding
import com.nazar.sobatmasjid.ui.fragments.profile.ProfileViewModel
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory


class CalendarProfileFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCalendarBinding
    private lateinit var profileViewModel: ProfileViewModel

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

        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModelStore = findNavController().previousBackStackEntry?.viewModelStore!!
        profileViewModel = ViewModelProvider(viewModelStore, factory)[ProfileViewModel::class.java]
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