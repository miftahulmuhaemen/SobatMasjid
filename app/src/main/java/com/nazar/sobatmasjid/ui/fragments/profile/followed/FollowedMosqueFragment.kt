package com.nazar.sobatmasjid.ui.fragments.profile.followed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nazar.sobatmasjid.databinding.FragmentFollowedMosqueBinding
import com.nazar.sobatmasjid.ui.base.BaseBottomSheetFragment

class FollowedMosqueFragment : BaseBottomSheetFragment() {

    private lateinit var binding : FragmentFollowedMosqueBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowedMosqueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}