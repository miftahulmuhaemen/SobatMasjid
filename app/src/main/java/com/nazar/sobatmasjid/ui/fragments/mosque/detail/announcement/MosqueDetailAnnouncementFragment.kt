package com.nazar.sobatmasjid.ui.fragments.mosque.detail.announcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazar.sobatmasjid.databinding.FragmentRecyclerviewBinding
import com.nazar.sobatmasjid.ui.adapters.AnnouncementAdapter
import com.nazar.sobatmasjid.ui.fragments.mosque.detail.MosqueDetailViewModel
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory

class MosqueDetailAnnouncementFragment : Fragment() {

    private lateinit var binding : FragmentRecyclerviewBinding
    private lateinit var mosqueDetailViewModel: MosqueDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModelStore = findNavController().currentBackStackEntry?.viewModelStore!!
        mosqueDetailViewModel = ViewModelProvider(viewModelStore, factory)[MosqueDetailViewModel::class.java]
        mosqueDetailViewModel.mosque.observe(viewLifecycleOwner, {
            init(it.id)
        })
    }

    private fun init(id: String){
        val announcementAdapter = AnnouncementAdapter()
        with(binding.recyclerview) {
            layoutManager = LinearLayoutManager(context)
            adapter = announcementAdapter
        }
        mosqueDetailViewModel.getAnnouncementsById(id).observe(viewLifecycleOwner, {
            announcementAdapter.submitList(it)
            announcementAdapter.notifyDataSetChanged()
        })
    }
}