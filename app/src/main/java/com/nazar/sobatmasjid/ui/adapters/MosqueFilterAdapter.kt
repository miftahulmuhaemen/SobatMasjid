package com.nazar.sobatmasjid.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nazar.sobatmasjid.databinding.ItemFilterBinding

class MosqueFilterAdapter(private val items: List<String>, private val statuses: List<Boolean>, private val listener: (item: String, status: Boolean) -> Unit) : RecyclerView.Adapter<MosqueFilterAdapter.MosqueFilterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MosqueFilterViewHolder {
        val binding =
            ItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MosqueFilterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MosqueFilterViewHolder, position: Int) {
        holder.bind(items[position], statuses[position])
    }

    override fun getItemCount(): Int = items.size

    inner class MosqueFilterViewHolder(private val binding: ItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, status: Boolean) {
            binding.tvNotification.text = item
            binding.cbNotification.isChecked = status
            binding.cbNotification.setOnCheckedChangeListener { _, status ->
                listener(item, status)
            }
        }
    }
}