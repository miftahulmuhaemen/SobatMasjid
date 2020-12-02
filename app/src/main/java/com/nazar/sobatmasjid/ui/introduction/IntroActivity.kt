package com.nazar.sobatmasjid.ui.introduction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {

    private val preferences: Preferences by lazy {
        Preferences(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val introPageAdapter = IntroPagerAdapter(this)
        pagerIntroduction.adapter = introPageAdapter

        dotsPagerIntroduction.setViewPager2(pagerIntroduction)

        supportActionBar?.elevation = 0f

        btnLogin.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK )
            startActivity(intent)
            preferences.isNotFirstTime = true
            finish()
        }
    }
}