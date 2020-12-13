package com.nazar.sobatmasjid.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nazar.sobatmasjid.data.local.entity.CityEntity
import com.nazar.sobatmasjid.databinding.ItemLocationBinding

class LocationAdapter internal constructor(private val listener: (city: CityEntity) -> Unit) :
    PagedListAdapter<CityEntity, LocationAdapter.LocationViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CityEntity>() {
            override fun areItemsTheSame(oldItem: CityEntity, newItem: CityEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: CityEntity, newItem: CityEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding =
            ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val city = getItem(position) as CityEntity
        holder.bind(city)
    }

    inner class LocationViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: CityEntity) {
            binding.tvLocation.text = city.name
            binding.root.setOnClickListener {
                listener(city)
            }
        }
    }
}
