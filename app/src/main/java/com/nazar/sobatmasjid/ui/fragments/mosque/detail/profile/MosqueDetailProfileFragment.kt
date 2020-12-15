package com.nazar.sobatmasjid.ui.fragments.mosque.detail.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentMosqueDetailProfileBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.fragments.mosque.detail.MosqueDetailViewModel
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory

class MosqueDetailProfileFragment : Fragment() {

    private lateinit var binding : FragmentMosqueDetailProfileBinding
    private lateinit var mosqueDetailViewModel: MosqueDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMosqueDetailProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModelStore = findNavController().currentBackStackEntry?.viewModelStore!!
        mosqueDetailViewModel = ViewModelProvider(viewModelStore, factory)[MosqueDetailViewModel::class.java]
        mosqueDetailViewModel.mosque.observe(viewLifecycleOwner, {
            binding.tvMosqueName.text = it.name
            binding.tvAddress.text = it.address
            binding.tvDescription.text = it.description
            binding.tvEmail.text = it.email
            binding.tvErectDate.text = it.standingDate
        })
    }

}