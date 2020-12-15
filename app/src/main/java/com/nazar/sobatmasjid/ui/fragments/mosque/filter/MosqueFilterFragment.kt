package com.nazar.sobatmasjid.ui.fragments.mosque.filter

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentMosqueFilterBinding
import com.nazar.sobatmasjid.ui.adapters.MosqueFilterAdapter
import com.nazar.sobatmasjid.ui.base.BaseBottomSheetFragment
import com.nazar.sobatmasjid.ui.fragments.mosque.MosqueViewModel
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory

class MosqueFilterFragment: BaseBottomSheetFragment() {

    private lateinit var binding: FragmentMosqueFilterBinding
    private lateinit var mosqueViewModel: MosqueViewModel
    private var items: MutableList<String> = mutableListOf()
    private val statuses: MutableList<Boolean> = mutableListOf()
    private var classifications: MutableList<String> = mutableListOf()

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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMosqueFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModelStore = findNavController().previousBackStackEntry?.viewModelStore!!
        mosqueViewModel = ViewModelProvider(viewModelStore, factory)[MosqueViewModel::class.java]

        classifications = resources.getStringArray(R.array.mosque_classification).toMutableList()
        mosqueViewModel.classification.observe(viewLifecycleOwner, {
            if(!it.isNullOrEmpty()){
                classifications.forEachIndexed { index, _->
                    statuses.add(it.contains(classifications[index]))
                }
                val filterAdapter = MosqueFilterAdapter(classifications, statuses) { item, status ->
                    statuses[item] = status
                }
                with(binding.rvFilter) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = filterAdapter
                    filterAdapter.notifyDataSetChanged()
                }
            }
        })
        binding.btnBack.setOnClickListener {
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        classifications.forEachIndexed { index, item->
            if(statuses[index])
                items.add(item)
        }
        mosqueViewModel.classification.value = items
    }
}