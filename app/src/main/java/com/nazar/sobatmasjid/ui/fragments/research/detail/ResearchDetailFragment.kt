package com.nazar.sobatmasjid.ui.fragments.research.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.data.local.entity.ResearchDetailEntity
import com.nazar.sobatmasjid.databinding.FragmentResearchDetailBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.nazar.sobatmasjid.vo.Status

class ResearchDetailFragment : Fragment() {

    private lateinit var binding: FragmentResearchDetailBinding
    private lateinit var id: String
    private val researchDetailViewModel: ResearchDetailViewModel by viewModel()
    private val args: ResearchDetailFragmentArgs by navArgs()
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResearchDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = args.id.toString()
        if (id.isNotBlank()) {
            researchDetailViewModel.getResearchDetail(
                id,
                preferences.idUser,
                preferences.latitude,
                preferences.longitude
            ).observe(viewLifecycleOwner, { research ->
                if (research != null) {
                    when (research.status) {
                        Status.LOADING -> {
                        }
                        Status.SUCCESS -> {
                            if (research.data != null)
                                with(research.data) {
                                    init(this)
                                }
                        }
                        Status.ERROR -> {
                            Toast.makeText(
                                context,
                                getString(R.string.notification_warning),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }
    }

    private fun init(research: ResearchDetailEntity) {
        binding.imgResearch.setImageFromUrl(research.brochure.toString())
        binding.imgResearchBackground.setImageFromUrl(research.brochure.toString())
        binding.imgUstadzPhoto.setImageFromUrl(research.ustadzPhoto.toString())
        binding.tvMosqueName.text = research.mosqueName
        binding.tvResearchTitle.text = research.title
        binding.tvResearchCategory.text = research.category
        binding.tvResearchDate.text = research.date
        binding.tvResearchGender.text = research.researchType
        binding.tvResearchNote.text = research.note
        binding.btnBack.setOnClickListener { requireActivity().onBackPressed() }
        binding.btnVisitLink.setOnClickListener {
            if (!research.idMosque.isNullOrBlank())
                researchDetailViewModel.attendResearch(
                    preferences.idUser,
                    research.id,
                    research.idMosque!!
                ).observe(viewLifecycleOwner, {
                    researchDetailViewModel.setAttendResearch(research, it)
                    setButtonAttend(it)
                })
        }
        setButtonAttend(research.attend)
    }

    private fun setButtonAttend(state: Boolean?) {
        if (state != null)
            if (state) {
                binding.btnVisitLink.text = getString(R.string.attended)
                binding.btnVisitLink.isEnabled = false
            } else {
                binding.btnVisitLink.text = getString(R.string.register)
                binding.btnVisitLink.isEnabled = true
            }
    }

}