package com.nazar.sobatmasjid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nazar.sobatmasjid.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**Keep in mind that when you perform fragment transactions, such as replace or remove one,
    it's often appropriate to allow the user to navigate backward and "undo" the change. To allow the user
    to navigate backward through the fragment transactions, you must call
    addToBackStack() before you commit the FragmentTransaction.*/

}
