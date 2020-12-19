package com.nazar.sobatmasjid.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nazar.sobatmasjid.data.local.entity.FollowedMosqueEntity
import com.nazar.sobatmasjid.databinding.ItemFollowedMosqueBinding
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl

class FollowedMosqueAdapter internal constructor() :
    PagedListAdapter<FollowedMosqueEntity, FollowedMosqueAdapter.FollowedMosqueViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FollowedMosqueEntity>() {
            override fun areItemsTheSame(oldItem: FollowedMosqueEntity, newItem: FollowedMosqueEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: FollowedMosqueEntity, newItem: FollowedMosqueEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowedMosqueViewHolder {
        val binding =
            ItemFollowedMosqueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowedMosqueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowedMosqueViewHolder, position: Int) {
        val followedMosque = getItem(position) as FollowedMosqueEntity
        holder.bind(followedMosque)
    }

    class FollowedMosqueViewHolder(private val binding: ItemFollowedMosqueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(followedMosque: FollowedMosqueEntity) {
            binding.tvMosqueName.text = followedMosque.name
            binding.tvMosqueUsername.text = followedMosque.username
            binding.imgUstadzPhoto.setImageFromUrl(followedMosque.photo.toString())
        }
    }
}
