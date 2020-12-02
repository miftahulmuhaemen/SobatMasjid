package com.nazar.sobatmasjid.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.introduction.IntroActivity
import com.nazar.sobatmasjid.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private var shortAnimationDuration: Long = 0
    private val preferences: Preferences by lazy {
        Preferences(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        lifecycleScope.launch {
            AppLogoBlock.animate()
                .alpha(1f)
                .translationY(-AppLogoBlock.height.toFloat())
                .setDuration(shortAnimationDuration)
                .setListener(null)

            delay(1500)

            AppLogoBlock.animate()
                .alpha(0f)
                .translationY(AppLogoBlock.height.toFloat())
                .setDuration(shortAnimationDuration)
                .setListener(null)

            delay(200)

            SponsorBlock.apply {

                animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(null)

            }

            delay(1500)

            intent = if(!preferences.isNotFirstTime)
                Intent(this@SplashActivity, IntroActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK )
            else
                Intent(this@SplashActivity, LoginActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK )

            startActivity(intent)
            finish()
        }
    }
}