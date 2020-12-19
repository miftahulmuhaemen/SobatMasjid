package com.nazar.sobatmasjid.ui.fragments.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentProfileBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.base.BaseBottomTabFragment
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl

class ProfileFragment : BaseBottomTabFragment() {

    private lateinit var binding : FragmentProfileBinding
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgProfilePhoto.setImageFromUrl(preferences.photoUrl)
        binding.tvUsername.text = preferences.name
        binding.tvMotto.text = preferences.motto
        binding.tvFollowerCount.text = preferences.numberFollow.toString()
        binding.progressBarProfile.progress = fillingProfileProgress()
        binding.tvProgressBarPercentage.text = "${fillingProfileProgress()}%"
        binding.btnFollowing.setOnClickListener {
            navigateWithAction(ProfileFragmentDirections.actionProfileFragmentToFollowedMosqueFragment())
        }
        binding.btnMosqueRegister.setOnClickListener {

        }
        binding.btnRating.setOnClickListener {

        }
        binding.btnSupportedBy.setOnClickListener {

        }
        binding.btnLogout.setOnClickListener {

        }
        binding.btnEditProfile.setOnClickListener {
            navigateWithAction(ProfileFragmentDirections.actionProfileFragmentToProfileEditFragment())
        }
    }

    private fun fillingProfileProgress(): Int {
        var progress = 0
        if(preferences.name.isNotEmpty())
            progress += 1
        if(preferences.bornDate.isNotEmpty())
            progress += 1
        if(preferences.gender.isNotEmpty())
            progress += 1
        if(preferences.motto.isNotEmpty())
            progress += 1
        if(preferences.photoUrl.isNotEmpty())
            progress += 1
        return progress*20
    }
}