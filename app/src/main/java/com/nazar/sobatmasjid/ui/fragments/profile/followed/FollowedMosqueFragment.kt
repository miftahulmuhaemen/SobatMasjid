package com.nazar.sobatmasjid.ui.fragments.profile.followed

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentFollowedMosqueBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.adapters.FollowedMosqueAdapter
import com.nazar.sobatmasjid.ui.base.BaseBottomSheetFragment
import com.nazar.sobatmasjid.utils.extensions.afterTextChanged
import com.nazar.sobatmasjid.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowedMosqueFragment : BaseBottomSheetFragment() {

    private lateinit var binding : FragmentFollowedMosqueBinding
    private lateinit var followedMosqueAdapter: FollowedMosqueAdapter
    private val followedMosqueViewModel: FollowedMosqueViewModel by viewModel()
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowedMosqueBinding.inflate(inflater, container, false)
        return binding.root
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followedMosqueAdapter = FollowedMosqueAdapter()
        with(binding.rvFollow) {
            layoutManager = LinearLayoutManager(context)
            adapter = followedMosqueAdapter
        }
        binding.svFollow.afterTextChanged { loadData(it) }
        binding.svFollow.setOnCloseListener {
            loadData("")
            false
        }
        binding.btnBack.setOnClickListener { dismiss() }

        loadData("")
    }

    private fun loadData(query: String){
        followedMosqueViewModel
            .getFollowedMosques(
                preferences.idUser,
                query
            )
            .observe(viewLifecycleOwner, { followedMosques ->
                if (followedMosques != null) {
                    when (followedMosques.status) {
                        Status.LOADING -> {
                        }
                        Status.SUCCESS -> {
                            followedMosqueAdapter.submitList(followedMosques.data)
                            followedMosqueAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, getString(R.string.notification_warning), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
    }
}