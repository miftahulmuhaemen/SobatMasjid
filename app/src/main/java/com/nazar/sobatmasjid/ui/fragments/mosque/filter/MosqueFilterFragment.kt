package com.nazar.sobatmasjid.ui.fragments.mosque.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.databinding.FragmentMosqueFilterBinding
import com.nazar.sobatmasjid.ui.adapters.MosqueFilterAdapter
import com.nazar.sobatmasjid.ui.fragments.mosque.MosqueViewModel
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory

class MosqueFilterFragment: Fragment() {

    private lateinit var binding: FragmentMosqueFilterBinding
    private lateinit var viewModel: MosqueViewModel
    private var items: MutableList<String> = mutableListOf()
    private var statuses: MutableList<Boolean> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMosqueFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[MosqueViewModel::class.java]

        val filterAdapter = MosqueFilterAdapter(items, statuses) { item, status ->
            if(status){
                if(!items.contains(item))
                    items.add(item)
            }
            else {
                if(items.contains(item))
                    items.remove(item)
            }
        }
        with(binding.rvFilter) {
            layoutManager = LinearLayoutManager(context)
            adapter = filterAdapter
        }
        viewModel.classification.observe(viewLifecycleOwner, {
            val classifications = resources.getStringArray(R.array.mosque_type).toList()
            items.addAll(it)
            classifications.forEachIndexed { index, _->
                statuses[index] = it.contains(classifications[index])
            }
            filterAdapter.notifyDataSetChanged()
        })
    }
}