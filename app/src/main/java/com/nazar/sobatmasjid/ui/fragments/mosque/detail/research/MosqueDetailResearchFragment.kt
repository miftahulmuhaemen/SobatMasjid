package com.nazar.sobatmasjid.ui.fragments.mosque.detail.research

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazar.sobatmasjid.databinding.FragmentRecyclerviewBinding
import com.nazar.sobatmasjid.ui.adapters.ResearchAdapter
import com.nazar.sobatmasjid.ui.fragments.mosque.detail.MosqueDetailFragmentDirections
import com.nazar.sobatmasjid.ui.fragments.mosque.detail.MosqueDetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MosqueDetailResearchFragment : Fragment() {

    private lateinit var binding : FragmentRecyclerviewBinding
    private val mosqueDetailViewModel by sharedViewModel<MosqueDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mosqueDetailViewModel.mosque.observe(viewLifecycleOwner, {
            init(it.id)
        })
    }

    private fun init(id: String){
        val researchAdapter = ResearchAdapter {
            findNavController().navigate(MosqueDetailFragmentDirections.actionMosqueDetailToResearchDetail(it))
        }
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