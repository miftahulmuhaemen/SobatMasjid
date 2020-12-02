package com.nazar.sobatmasjid.ui.login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.data.remote.StatusResponse
import com.nazar.sobatmasjid.data.service.model.LocationModel
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.login.LoginActivity.Login.FACEBOOK_PROVIDER
import com.nazar.sobatmasjid.ui.login.LoginActivity.Login.GOOGLE_PROVIDER
import com.nazar.sobatmasjid.ui.login.LoginActivity.Login.PERMISSION
import com.nazar.sobatmasjid.ui.login.LoginActivity.Login.RC_SIGN_IN
import com.nazar.sobatmasjid.ui.main.MainActivity
import com.nazar.sobatmasjid.utils.hasPermissions
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity(), View.OnClickListener, FirebaseAuth.AuthStateListener {

    private lateinit var viewModel: LoginViewModel
    private var location: Location = Location("currentPosition")
    private val preferences: Preferences by lazy {
        Preferences(applicationContext)
    }

    object Login {
        const val RC_SIGN_IN: Int = 1
        const val PERMISSION: Int = 992
        val GOOGLE_PROVIDER = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
        val FACEBOOK_PROVIDER = arrayListOf(AuthUI.IdpConfig.FacebookBuilder().build())
    }

    override fun onStart() {
        super.onStart()
        btnGoogle.setOnClickListener(this)
        btnFacebook.setOnClickListener(this)
        FirebaseAuth.getInstance().addAuthStateListener(this)
        onAuthStateChanged(FirebaseAuth.getInstance())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        val permissions = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (!hasPermissions(this, *permissions)) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION)
        } else {
            viewModel.getLocation(this).observe(this, Observer<LocationModel> {
                location.longitude = it?.longitude!!
                location.latitude = it.latitude
                pbLoading.visibility = View.GONE
            })
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            viewModel.getLocation(this).observe(this, Observer<LocationModel> {
                location.longitude = it?.longitude!!
                location.latitude = it.latitude
                pbLoading.visibility = View.GONE
            })
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            btnGoogle -> startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                    .setAvailableProviders(GOOGLE_PROVIDER)
                    .setIsSmartLockEnabled(false)
                    .build(), RC_SIGN_IN
            )
            btnFacebook -> startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                    .setAvailableProviders(FACEBOOK_PROVIDER)
                    .setIsSmartLockEnabled(false)
                    .build(), RC_SIGN_IN
            )
        }
    }

    override fun onAuthStateChanged(state: FirebaseAuth) {
        val user = state.currentUser
        val currentDate = Calendar.getInstance().time

        if (user != null) {
            if (!location.latitude.isNaN()) {
                pbLoading.visibility = View.VISIBLE
                viewModel.userLogin(
                    user.displayName!!,
                    currentDate,
                    user.email!!,
                    location.latitude,
                    location.longitude
                ).observe(this, Observer {
                    when (it.status) {
                        StatusResponse.SUCCESS -> {
                            val userResponse = it.body!!
                            preferences.setPreference(userResponse)
                            state.removeAuthStateListener(this)

                            intent = Intent(this, MainActivity::class.java)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                            finish()
                        }
                        StatusResponse.EMPTY -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        }
                        StatusResponse.ERROR -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        }
                    }

                    pbLoading.visibility = View.GONE
                })
            }
        }
    }
}