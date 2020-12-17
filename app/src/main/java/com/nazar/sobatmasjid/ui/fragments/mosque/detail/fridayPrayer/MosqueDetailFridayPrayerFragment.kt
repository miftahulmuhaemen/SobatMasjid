package com.nazar.sobatmasjid.ui.fragments.mosque.detail.fridayPrayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazar.sobatmasjid.databinding.FragmentMosqueDetailFridayPrayerBinding
import com.nazar.sobatmasjid.ui.adapters.FinanceAdapter
import com.nazar.sobatmasjid.ui.adapters.OfficerAdapter
import com.nazar.sobatmasjid.ui.fragments.mosque.detail.MosqueDetailViewModel
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory

class MosqueDetailFridayPrayerFragment : Fragment() {

    private lateinit var binding : FragmentMosqueDetailFridayPrayerBinding
    private lateinit var mosqueDetailViewModel: MosqueDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMosqueDetailFridayPrayerBinding.inflate(inflater, container, false)
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
        val financeAdapter = FinanceAdapter()
        with(binding.rvCredit) {
            layoutManager = LinearLayoutManager(context)
            adapter = financeAdapter
        }
        mosqueDetailViewModel.getFinancesById(id).observe(viewLifecycleOwner, {
            financeAdapter.submitList(it)
            financeAdapter.notifyDataSetChanged()
        })

        val officerAdapter = OfficerAdapter()
        with(binding.rvOfficer){
            layoutManager = LinearLayoutManager(context)
            adapter = officerAdapter
        }
        mosqueDetailViewModel.getOfficersById(id).observe(viewLifecycleOwner, {
            officerAdapter.submitList(it)
            officerAdapter.notifyDataSetChanged()
        })
    }
}