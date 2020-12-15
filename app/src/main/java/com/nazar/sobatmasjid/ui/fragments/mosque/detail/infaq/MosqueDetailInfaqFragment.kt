package com.nazar.sobatmasjid.ui.fragments.mosque.detail.infaq

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nazar.sobatmasjid.databinding.FragmentInfaqBinding
import com.nazar.sobatmasjid.ui.base.BaseBottomSheetFragment
import com.nazar.sobatmasjid.ui.fragments.mosque.detail.MosqueDetailViewModel
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory

class MosqueDetailInfaqFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentInfaqBinding
    private lateinit var mosqueDetailViewModel: MosqueDetailViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState) as BottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfaqBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModelStore = findNavController().previousBackStackEntry?.viewModelStore!!
        mosqueDetailViewModel = ViewModelProvider(viewModelStore, factory)[MosqueDetailViewModel::class.java]
        mosqueDetailViewModel.mosque.observe(viewLifecycleOwner, {
            binding.tvAccount.text = it.accountNumber
            binding.tvOwner.text = it.accountName
            binding.tvBank.text = it.bankName
            binding.imgQris.setImageFromUrl(it.qris.toString())
        })

        binding.btnClose.setOnClickListener { dismiss() }
    }
}