package com.nazar.sobatmasjid.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nazar.sobatmasjid.data.local.entity.FinanceEntity
import com.nazar.sobatmasjid.databinding.ItemFridayPrayerWideFinancecBinding


class FinanceAdapter internal constructor() :
    PagedListAdapter<FinanceEntity, FinanceAdapter.FinanceViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FinanceEntity>() {
            override fun areItemsTheSame(oldItem: FinanceEntity, newItem: FinanceEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: FinanceEntity, newItem: FinanceEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinanceViewHolder {
        val binding =
            ItemFridayPrayerWideFinancecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FinanceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FinanceViewHolder, position: Int) {
        val finance = getItem(position) as FinanceEntity
        holder.bind(finance)
    }

    class FinanceViewHolder(private val binding: ItemFridayPrayerWideFinancecBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(finance: FinanceEntity) {
            binding.tvCreditIn.text = finance.creditIn
            binding.tvCreditOut.text = finance.creditOut
            binding.tvCredit.text = finance.creditText
            binding.tvDateDayMonth.text = finance.date
            binding.root.setOnClickListener { view ->
//                view.findNavController().navigate(
//                )
            }
        }
    }
}
