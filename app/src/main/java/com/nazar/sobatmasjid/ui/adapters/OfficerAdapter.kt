package com.nazar.sobatmasjid.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nazar.sobatmasjid.data.local.entity.OfficerEntity
import com.nazar.sobatmasjid.databinding.ItemFridayPrayerWideFridayOfficerBinding


class OfficerAdapter internal constructor() :
    PagedListAdapter<OfficerEntity, OfficerAdapter.OfficerViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<OfficerEntity>() {
            override fun areItemsTheSame(oldItem: OfficerEntity, newItem: OfficerEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: OfficerEntity, newItem: OfficerEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfficerViewHolder {
        val binding =
            ItemFridayPrayerWideFridayOfficerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfficerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfficerViewHolder, position: Int) {
        val officer = getItem(position) as OfficerEntity
        holder.bind(officer)
    }

    class OfficerViewHolder(private val binding: ItemFridayPrayerWideFridayOfficerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(officer: OfficerEntity) {
            binding.tvMuadzin.text = officer.muadzin
            binding.tvKhatib.text = officer.khatib
            binding.tvDateDayMonth.text = officer.date
            binding.root.setOnClickListener { view ->
//                view.findNavController().navigate(
//                )
            }
        }
    }
}
