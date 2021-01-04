package com.nazar.sobatmasjid.ui.fragments.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.auth.AuthUI
import com.nazar.sobatmasjid.databinding.FragmentProfileBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.activities.authentication.AuthenticationActivity
import com.nazar.sobatmasjid.ui.base.BaseBottomTabFragment
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseBottomTabFragment(), View.OnClickListener {

    private lateinit var binding : FragmentProfileBinding
    private val profileViewModel by viewModel<ProfileViewModel>()
    private val preferences: Preferences by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.changedUser.observe(viewLifecycleOwner, {
            Log.d("LOG", it.toString())
            preferences.setPreferenceUserOnly(it)
            init()
        })

        init()
    }

    override fun onClick(view: View?) {
        when(view){
            binding.btnEditProfile -> {
                navigateWithAction(ProfileFragmentDirections.actionProfileFragmentToProfileEditFragment())
            }
            binding.btnFollowing -> {
                navigateWithAction(ProfileFragmentDirections.actionProfileFragmentToFollowedMosqueFragment())
            }
            binding.btnMosqueRegister -> {}
            binding.btnRating -> {}
            binding.btnSupportedBy -> {}
            binding.btnLogout -> {
                AuthUI.getInstance()
                    .signOut(requireActivity())
                    .addOnCompleteListener {
                        requireActivity().intent = Intent(requireActivity(), AuthenticationActivity::class.java)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(requireActivity().intent)
                        requireActivity().finish()
                    }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun init(){
        binding.imgProfilePhoto.setImageFromUrl(preferences.photoUrl)
        binding.tvUsername.text = preferences.name
        binding.tvMotto.text = preferences.motto
        binding.tvFollowerCount.text = preferences.numberFollow.toString()
        binding.progressBarProfile.progress = fillingProfileProgress()
        binding.tvProgressBarPercentage.text = "${fillingProfileProgress()}%"
        binding.btnEditProfile.setOnClickListener(this)
        binding.btnFollowing .setOnClickListener(this)
        binding.btnMosqueRegister .setOnClickListener(this)
        binding.btnRating.setOnClickListener(this)
        binding.btnSupportedBy.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)
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