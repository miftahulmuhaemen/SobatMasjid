package com.nazar.sobatmasjid.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nazar.sobatmasjid.BuildConfig.IMAGE_URL
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.data.local.entity.MosqueRecommendationEntity
import com.nazar.sobatmasjid.databinding.ItemMosqueRecommendationBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.fragments.home.HomeViewModel
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl

class MosqueRecommendationAdapter internal constructor(private val listener: (id: String) -> Unit) :
    PagedListAdapter<MosqueRecommendationEntity, MosqueRecommendationAdapter.MosqueViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MosqueRecommendationEntity>() {
            override fun areItemsTheSame(oldItem: MosqueRecommendationEntity, newItem: MosqueRecommendationEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: MosqueRecommendationEntity, newItem: MosqueRecommendationEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MosqueViewHolder {
        val binding =
            ItemMosqueRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MosqueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MosqueViewHolder, position: Int) {
        val mosque = getItem(position) as MosqueRecommendationEntity
        holder.bind(mosque)
    }

    inner class MosqueViewHolder(private val binding: ItemMosqueRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mosque: MosqueRecommendationEntity) {
            binding.tvFullNameMosque.text = mosque.name
            binding.tvMosqueUsername.text = mosque.username
            binding.tvDistance.text = mosque.distance
            binding.imgMosque.setImageFromUrl(mosque.photo.toString())
            binding.btnFollow.setOnClickListener {
                binding.btnFollow.text = binding.root.context.getString(R.string.action_bar_followed)
                binding.btnFollow.setBackgroundResource(R.color.colorPrimaryDark)
                binding.btnFollow.setTextColor(Color.WHITE)
                listener(mosque.id)
            }
        }
    }
}
