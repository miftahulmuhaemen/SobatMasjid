package com.nazar.sobatmasjid.ui.fragments.mosque.detail.research

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentMosqueDetailProfileBinding
import com.nazar.sobatmasjid.databinding.FragmentRecyclerviewBinding
import com.nazar.sobatmasjid.ui.adapters.ResearchAdapter
import com.nazar.sobatmasjid.ui.fragments.mosque.detail.MosqueDetailViewModel
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory

class MosqueDetailResearchFragment : Fragment() {

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
        val researchAdapter = ResearchAdapter()
        with(binding.recyclerview) {
            layoutManager = LinearLayoutManager(context)
            adapter = researchAdapter
        }
        mosqueDetailViewModel.getResearchesById(id).observe(viewLifecycleOwner, {
            researchAdapter.submitList(it)
            researchAdapter.notifyDataSetChanged()
        })
    }
}