package com.nazar.sobatmasjid.ui.fragments.login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.data.remote.StatusResponse
import com.nazar.sobatmasjid.databinding.FragmentLoginBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.activities.main.MainActivity
import com.nazar.sobatmasjid.ui.fragments.login.LoginFragment.Login.FACEBOOK_PROVIDER
import com.nazar.sobatmasjid.ui.fragments.login.LoginFragment.Login.GOOGLE_PROVIDER
import com.nazar.sobatmasjid.ui.fragments.login.LoginFragment.Login.PERMISSION
import com.nazar.sobatmasjid.ui.fragments.login.LoginFragment.Login.RC_SIGN_IN
import com.nazar.sobatmasjid.utils.hasPermissions
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.sql.Date
import java.time.LocalDate

class LoginFragment : Fragment(), View.OnClickListener, FirebaseAuth.AuthStateListener {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel : LoginViewModel by viewModel()
    private var location: Location = Location("currentPosition")
    private var isLocationAvailable: Boolean = false
    private val preferences: Preferences by inject()

    object Login {
        const val RC_SIGN_IN: Int = 1
        const val PERMISSION: Int = 992
        val GOOGLE_PROVIDER = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
        val FACEBOOK_PROVIDER = arrayListOf(AuthUI.IdpConfig.FacebookBuilder().build())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val permissions = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
        if (!hasPermissions(requireContext(), *permissions))
            requestPermissions(permissions, PERMISSION)
        else
            getLocation()
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnGoogle -> startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                    .setAvailableProviders(GOOGLE_PROVIDER)
                    .setIsSmartLockEnabled(false)
                    .build(), RC_SIGN_IN
            )
            binding.btnFacebook -> startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                    .setAvailableProviders(FACEBOOK_PROVIDER)
                    .setIsSmartLockEnabled(false)
                    .build(), RC_SIGN_IN
            )
        }
    }

    override fun onAuthStateChanged(state: FirebaseAuth) {
        val user = state.currentUser
        val date = Date.valueOf(LocalDate.now().toString())
        if (user != null) {
            if (isLocationAvailable) {
                binding.pbLoading.visibility = View.VISIBLE
                viewModel.userLogin(
                    user.displayName!!,
                    date,
                    user.email!!,
                    location.latitude,
                    location.longitude
                ).observe(viewLifecycleOwner, {
                    when (it.status) {
                        StatusResponse.SUCCESS -> {
                            val userResponse = it.body!!

                            preferences.setPreference(userResponse, location)
                            state.removeAuthStateListener(this)

                            requireActivity().intent = Intent(requireActivity(), MainActivity::class.java)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(requireActivity().intent)
                            requireActivity().finish()
                        }
                        StatusResponse.EMPTY -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                        StatusResponse.ERROR -> {
                            Toast.makeText(requireContext(), getString(R.string.notification_warning), Toast.LENGTH_SHORT).show()
                        }
                    }
                    binding.pbLoading.visibility = View.GONE
                })
            }
        }
    }

    private fun getLocation(){
        viewModel.getLocation(requireContext()).observe(viewLifecycleOwner, {
            location.longitude = it?.longitude!!
            location.latitude = it.latitude

            isLocationAvailable = true
            binding.pbLoading.visibility = View.GONE

            binding.btnGoogle.setOnClickListener(this)
            binding.btnFacebook.setOnClickListener(this)
            FirebaseAuth.getInstance().addAuthStateListener(this)
            onAuthStateChanged(FirebaseAuth.getInstance())
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED))
            getLocation()
    }
}