package com.nazar.sobatmasjid.ui.fragments.introduction

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nazar.sobatmasjid.databinding.FragmentIntroBinding
import com.nazar.sobatmasjid.preference.Preferences
import kotlinx.android.synthetic.main.fragment_intro.*
import org.koin.android.ext.android.inject

class IntroductionFragment : Fragment() {

    private lateinit var binding: FragmentIntroBinding
    private val preferences: Preferences by inject()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(preferences.isNotFirstTime)
            findNavController().navigate(IntroductionFragmentDirections.actionIntroductionFragmentToLoginFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val introPageAdapter = IntroPagerAdapter(requireActivity())
        pagerIntroduction.adapter = introPageAdapter

        dotsPagerIntroduction.setViewPager2(pagerIntroduction)

        btnLogin.setOnClickListener {
            preferences.isNotFirstTime = true
            findNavController().navigate(IntroductionFragmentDirections.actionIntroductionFragmentToLoginFragment())
        }
    }
}