package com.nazar.sobatmasjid.ui.fragments.announcement.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentAnnouncementDetailBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.fragments.announcement.AnnouncementViewModel
import com.nazar.sobatmasjid.ui.fragments.research.detail.ResearchDetailFragmentArgs
import com.nazar.sobatmasjid.ui.fragments.research.detail.ResearchDetailViewModel
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory
import com.nazar.sobatmasjid.vo.Status

class AnnouncementDetailFragment : Fragment() {

    private lateinit var binding : FragmentAnnouncementDetailBinding
    private lateinit var announcementDetailViewModel: AnnouncementDetailViewModel
    private lateinit var id: String
    private val args: AnnouncementDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnnouncementDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireContext())
        announcementDetailViewModel =
            ViewModelProvider(this, factory)[AnnouncementDetailViewModel::class.java]
        id = args.id.toString()
        if (id.isNotBlank()) {
            announcementDetailViewModel.getAnnouncementByIdAnnouncement(id)
                .observe(viewLifecycleOwner, {
                    binding.imgAnnouncement.setImageFromUrl(it.file.toString())
                    binding.imgAnnouncementBackground.setImageFromUrl(it.file.toString())
                    binding.tvAnnouncementCategory.text = it.category
                    binding.tvAnnouncementDate.text = it.date
                    binding.tvAnnouncementTitle.text = it.title
                    binding.tvMosqueName.text = it.mosqueName
                    binding.btnBack.setOnClickListener { requireActivity().onBackPressed() }
            })
        }
    }

}