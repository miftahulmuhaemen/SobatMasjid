package com.nazar.sobatmasjid.ui.activities.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity(){

    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}