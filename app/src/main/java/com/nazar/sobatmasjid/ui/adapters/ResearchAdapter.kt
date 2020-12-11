package com.nazar.sobatmasjid.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nazar.sobatmasjid.BuildConfig.IMAGE_URL
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.data.local.entity.MosqueEntity
import com.nazar.sobatmasjid.data.local.entity.ResearchEntity
import com.nazar.sobatmasjid.databinding.ItemMosqueFitBinding
import com.nazar.sobatmasjid.databinding.ItemResearchWideBinding
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl

class ResearchAdapter internal constructor() :
    PagedListAdapter<ResearchEntity, ResearchAdapter.ResearchViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResearchEntity>() {
            override fun areItemsTheSame(oldItem: ResearchEntity, newItem: ResearchEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ResearchEntity, newItem: ResearchEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchViewHolder {
        val binding =
            ItemResearchWideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResearchViewHolder, position: Int) {
        val research = getItem(position) as ResearchEntity
        holder.bind(research)
    }

    class ResearchViewHolder(private val binding: ItemResearchWideBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(research: ResearchEntity) {
            binding.tvMosqueName.text = research.mosqueName
            binding.tvResearchTitle.text = research.title
            binding.tvResearchCategory.text = research.category
            binding.tvResearchDate.text = research.date
            binding.tvResearchTime.text = research.startTime
            binding.tvUstadzName.text = research.ustadzName
            binding.ustadzPhoto.setImageFromUrl(research.ustadzPhoto.toString())
        }
    }
}
