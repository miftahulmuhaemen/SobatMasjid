package com.nazar.sobatmasjid.ui.fragments.mosque.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.messaging.FirebaseMessaging
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.data.local.entity.MosqueDetailEntity
import com.nazar.sobatmasjid.databinding.FragmentMosqueDetailBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.adapters.ImageSliderAdapter
import com.nazar.sobatmasjid.ui.pager.MosqueDetailPager
import com.nazar.sobatmasjid.utils.getListFromString
import com.nazar.sobatmasjid.vo.Status
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MosqueDetailFragment : Fragment() {

    private lateinit var id: String
    private lateinit var binding: FragmentMosqueDetailBinding
    private val mosqueDetailViewModel by sharedViewModel<MosqueDetailViewModel>()
    private val args: MosqueDetailFragmentArgs by navArgs()
    private val preferences: Preferences by inject()
    private val firebaseMessage : FirebaseMessaging by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMosqueDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = args.id.toString()
        if (id.isNotBlank()) {
            mosqueDetailViewModel.getMosqueDetail(
                id,
                preferences.idUser,
                preferences.latitude,
                preferences.longitude
            ).observe(viewLifecycleOwner, { mosque ->
                if (mosque != null) {
                    when (mosque.status) {
                        Status.LOADING -> {}
                        Status.SUCCESS -> {
                            if (mosque.data != null)
                                with(mosque.data) {
                                    mosqueDetailViewModel.setMosque(this)
                                    init(this)
                                }
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, getString(R.string.notification_warning), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
        binding.btnBack.setOnClickListener { requireActivity().onBackPressed() }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun init(mosque: MosqueDetailEntity) {
        val pager = MosqueDetailPager(childFragmentManager)
        val slider = ImageSliderAdapter(requireContext(), getListFromString(mosque.photo))
        binding.imagePagerMosque.adapter = slider
        binding.dotsImagePagerMosque.setViewPager(binding.imagePagerMosque)
        binding.pagerMosque.adapter = pager
        binding.tabsMosque.setupWithViewPager(binding.pagerMosque)
        binding.tvMosqueUsername.text = mosque.username
        binding.btnFollow.setOnClickListener {
            if (id.isNotBlank())
                if (mosque.followed){
                    firebaseMessage.unsubscribeFromTopic(mosque.id).addOnCompleteListener {
                        if(it.isSuccessful){
                            mosqueDetailViewModel.unFollowMosque(preferences.idUser, id)
                                .observe(viewLifecycleOwner, { status ->
                                    mosqueDetailViewModel.setFollowMosque(mosque, status)
                                    setFollow(status)
                                })
                        }
                    }
                }
                else{
                    firebaseMessage.subscribeToTopic(mosque.id).addOnCompleteListener {
                        if(it.isSuccessful){
                            mosqueDetailViewModel.followMosque(preferences.idUser, id)
                                .observe(viewLifecycleOwner, { status ->
                                    mosqueDetailViewModel.setFollowMosque(mosque, status)
                                    setFollow(status)
                                })
                        }
                    }
                }
        }
        binding.btnInfaq.setOnClickListener {
            findNavController().navigate(MosqueDetailFragmentDirections.actionMosqueDetailToMosqueDetailInfaqFragment())
        }
        binding.btnVisit.setOnClickListener {
            val geoLocation = "geo:${mosque.latitude}, ${mosque.longitude}?z=14&q=${mosque.name}"
            val gmmIntentUri = Uri.parse(geoLocation)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(requireActivity().packageManager)?.let {
                ContextCompat.startActivity(requireActivity(), mapIntent, null)
            }
        }
        setFollow(mosque.followed)
    }

    private fun setFollow(newState: Boolean) {
        if (newState) {
            binding.btnFollowBackground.background =
                ResourcesCompat.getDrawable(resources, R.drawable.btn_rectangle_round_fill, null)
            binding.btnFollow.setTextColor(Color.WHITE)
            binding.btnFollow.text = getString(R.string.followed)
        } else {
            binding.btnFollowBackground.background =
                ResourcesCompat.getDrawable(resources, R.drawable.btn_rectangle_round, null)
            binding.btnFollow.setTextColor(Color.BLACK)
            binding.btnFollow.text = getString(R.string.follow)
        }
    }

}