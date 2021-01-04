package com.nazar.sobatmasjid.ui.fragments.profile.edit.gender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nazar.sobatmasjid.databinding.FragmentGenderBinding
import com.nazar.sobatmasjid.ui.fragments.profile.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class GenderProfileFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentGenderBinding
    private val profileViewModel by sharedViewModel<ProfileViewModel>()

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
        binding.btnClose.setOnClickListener { dismiss() }
        binding.btnActionChange.setOnClickListener {
            val value = if (binding.rbMale.isChecked) "L"
            else "P"
            profileViewModel.setGender(value)
            dismiss()
        }
    }
}