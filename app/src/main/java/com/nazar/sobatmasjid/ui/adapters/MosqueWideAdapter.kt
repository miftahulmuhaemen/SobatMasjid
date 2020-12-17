package com.nazar.sobatmasjid.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nazar.sobatmasjid.data.local.entity.MosqueEntity
import com.nazar.sobatmasjid.databinding.ItemMosqueWideBinding
import com.nazar.sobatmasjid.ui.fragments.mosque.MosqueFragmentDirections
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl


class MosqueWideAdapter internal constructor() :
    PagedListAdapter<MosqueEntity, MosqueWideAdapter.MosqueViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MosqueEntity>() {
            override fun areItemsTheSame(oldItem: MosqueEntity, newItem: MosqueEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: MosqueEntity, newItem: MosqueEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MosqueViewHolder {
        val binding =
            ItemMosqueWideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MosqueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MosqueViewHolder, position: Int) {
        val mosque = getItem(position) as MosqueEntity
        holder.bind(mosque)
    }

    class MosqueViewHolder(private val binding: ItemMosqueWideBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mosque: MosqueEntity) {
            binding.tvMosqueName.text = mosque.name
            binding.tvMosqueType.text = mosque.type
            binding.tvDistance.text = mosque.distance
            binding.tvResearchCategory.text = mosque.classification
            binding.imgMosque.setImageFromUrl(mosque.photo.toString())
            binding.root.setOnClickListener { view ->
                view.findNavController().navigate(
                    MosqueFragmentDirections.actionMosqueFragmentToMosqueDetail(
                        mosque.id
                    )
                )
            }
        }
    }
}
