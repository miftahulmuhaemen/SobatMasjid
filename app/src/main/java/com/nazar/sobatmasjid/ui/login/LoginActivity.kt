package com.nazar.sobatmasjid.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.ui.login.LoginActivity.Login.FACEBOOK_PROVIDER
import com.nazar.sobatmasjid.ui.login.LoginActivity.Login.GOOGLE_PROVIDER
import com.nazar.sobatmasjid.ui.login.LoginActivity.Login.RC_SIGN_IN
import com.nazar.sobatmasjid.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener, FirebaseAuth.AuthStateListener {

    object Login {
        const val RC_SIGN_IN: Int = 1
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
    }

    override fun onClick(view: View?) {
        when(view){
            btnGoogle -> startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(GOOGLE_PROVIDER)
                .setIsSmartLockEnabled(false)
                .build(), RC_SIGN_IN)
            btnFacebook -> startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(FACEBOOK_PROVIDER)
                .setIsSmartLockEnabled(false)
                .build(), RC_SIGN_IN)
        }
    }

    override fun onAuthStateChanged(state: FirebaseAuth) {
        if(state.currentUser != null){
            state.removeAuthStateListener(this)
            intent = Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK )
            startActivity(intent)
            finish()
        }
    }
}