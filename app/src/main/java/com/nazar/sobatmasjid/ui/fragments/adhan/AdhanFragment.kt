package com.nazar.sobatmasjid.ui.fragments.adhan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentAdhanBinding

class AdhanFragment : Fragment() {

    private lateinit var binding: FragmentAdhanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adhan, container, false)
    }

}