package com.nazar.sobatmasjid.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navHostController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setupWithNavController(navHostController)
        binding.bottomNavigation.setOnNavigationItemReselectedListener {
            "Reselect blocked."
        }
    }
}
