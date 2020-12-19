package com.nazar.sobatmasjid.ui.fragments.profile.edit.gender

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nazar.sobatmasjid.databinding.FragmentCalendarBinding
import com.nazar.sobatmasjid.databinding.FragmentGenderBinding
import com.nazar.sobatmasjid.ui.fragments.profile.edit.ProfileEditViewModel
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory


class GenderProfileFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentGenderBinding
    private lateinit var profileEditViewModel: ProfileEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModelStore = findNavController().previousBackStackEntry?.viewModelStore!!
        profileEditViewModel =
            ViewModelProvider(viewModelStore, factory)[ProfileEditViewModel::class.java]
        binding.btnClose.setOnClickListener { dismiss() }
        binding.btnActionChange.setOnClickListener {
            val value = if (binding.rbMale.isChecked) "L"
            else "P"
            profileEditViewModel.setGender(value.toString())
            dismiss()
        }
    }
}