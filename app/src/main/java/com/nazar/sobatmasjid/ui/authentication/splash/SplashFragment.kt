package com.nazar.sobatmasjid.ui.authentication.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentSplashBinding
import com.nazar.sobatmasjid.preference.Preferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private var shortAnimationDuration: Long = 0
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }
    private val navHostController by lazy {
        (requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_authentication) as NavHostFragment).navController
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        lifecycleScope.launch {
            binding.AppLogoBlock.animate()
                .alpha(1f)
                .translationY(-binding.AppLogoBlock.height.toFloat())
                .setDuration(shortAnimationDuration)
                .setListener(null)

            delay(1500)

            binding.AppLogoBlock.animate()
                .alpha(0f)
                .translationY(binding.AppLogoBlock.height.toFloat())
                .setDuration(shortAnimationDuration)
                .setListener(null)

            delay(200)

            binding.SponsorBlock.apply {
                animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(null)
            }

            delay(1500)

            navHostController.navigate(R.id.action_splashFragment_to_introductionFragment)
        }
    }

}