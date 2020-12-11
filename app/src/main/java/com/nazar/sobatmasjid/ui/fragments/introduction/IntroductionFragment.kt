package com.nazar.sobatmasjid.ui.fragments.introduction

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentIntroBinding
import com.nazar.sobatmasjid.preference.Preferences
import kotlinx.android.synthetic.main.fragment_intro.*

class IntroductionFragment : Fragment() {

    private lateinit var binding: FragmentIntroBinding
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }
    private val navHostController by lazy {
        (requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_authentication) as NavHostFragment).navController
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(preferences.isNotFirstTime)
            navHostController.navigate(IntroductionFragmentDirections.actionIntroductionFragmentToLoginFragment())
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
            navHostController.navigate(IntroductionFragmentDirections.actionIntroductionFragmentToLoginFragment())
        }
    }
}