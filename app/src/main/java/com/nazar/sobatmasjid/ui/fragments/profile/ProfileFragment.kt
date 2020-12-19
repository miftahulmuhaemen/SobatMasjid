package com.nazar.sobatmasjid.ui.fragments.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.nazar.sobatmasjid.databinding.FragmentProfileBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.activities.authentication.AuthenticationActivity
import com.nazar.sobatmasjid.ui.base.BaseBottomTabFragment
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory

class ProfileFragment : BaseBottomTabFragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModelStore =
            findNavController().currentBackStackEntry?.viewModelStore!!
        profileViewModel =
            ViewModelProvider(viewModelStore, factory)[ProfileViewModel::class.java]
        profileViewModel.changedUser.observe(viewLifecycleOwner, {

        })

        init()

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
            AuthUI.getInstance()
                .signOut(requireActivity())
                .addOnCompleteListener {
                    requireActivity().intent = Intent(requireActivity(), AuthenticationActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(requireActivity().intent)
                    requireActivity().finish()
                }
        }
        binding.btnEditProfile.setOnClickListener {
            navigateWithAction(ProfileFragmentDirections.actionProfileFragmentToProfileEditFragment())
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