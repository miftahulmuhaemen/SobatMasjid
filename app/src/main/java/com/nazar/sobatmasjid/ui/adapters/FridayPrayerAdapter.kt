package com.nazar.sobatmasjid.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nazar.sobatmasjid.data.local.entity.FridayPrayerEntity
import com.nazar.sobatmasjid.databinding.ItemFridayPrayerFitBinding

class FridayPrayerAdapter internal constructor() :
    PagedListAdapter<FridayPrayerEntity, FridayPrayerAdapter.FridayPrayerViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FridayPrayerEntity>() {
            override fun areItemsTheSame(oldItem: FridayPrayerEntity, newItem: FridayPrayerEntity): Boolean {
                return oldItem.date == newItem.date
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: FridayPrayerEntity, newItem: FridayPrayerEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FridayPrayerViewHolder {
        val binding =
            ItemFridayPrayerFitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FridayPrayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FridayPrayerViewHolder, position: Int) {
        val fridayPrayer = getItem(position) as FridayPrayerEntity
        holder.bind(fridayPrayer)
    }

    inner class FridayPrayerViewHolder(private val binding: ItemFridayPrayerFitBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fridayPrayer: FridayPrayerEntity) {
            binding.tvFridayPrayerDate.text = fridayPrayer.date
            binding.tvMosqueName.text = fridayPrayer.mosqueName
            binding.tvKhatib.text = fridayPrayer.khatib
            binding.tvMuadzin.text = fridayPrayer.muadzin
            binding.tvCredit.text = fridayPrayer.creditText
            binding.tvCreditIn.text = fridayPrayer.creditIn
            binding.tvCreditOut.text = fridayPrayer.creditOut
        }
    }
}
