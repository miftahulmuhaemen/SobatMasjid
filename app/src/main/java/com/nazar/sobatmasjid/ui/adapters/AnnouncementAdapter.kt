package com.nazar.sobatmasjid.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nazar.sobatmasjid.data.local.entity.AnnouncementEntity
import com.nazar.sobatmasjid.databinding.ItemAnnouncementWideBinding
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl

class AnnouncementAdapter internal constructor(private val listener: (id: String) -> Unit) :
    PagedListAdapter<AnnouncementEntity, AnnouncementAdapter.AnnouncementViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AnnouncementEntity>() {
            override fun areItemsTheSame(oldItem: AnnouncementEntity, newItem: AnnouncementEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: AnnouncementEntity, newItem: AnnouncementEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val binding =
            ItemAnnouncementWideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnnouncementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        val announcement = getItem(position) as AnnouncementEntity
        holder.bind(announcement)
    }

    inner class AnnouncementViewHolder(private val binding: ItemAnnouncementWideBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(announcement: AnnouncementEntity) {
            binding.tvAnnouncementTitle.text = announcement.title
            binding.tvAnnouncementCategory.text = announcement.category
            binding.tvTimePassed.text = announcement.date
            binding.tvMosqueName.text = announcement.mosqueName
            binding.imgAnnouncement.setImageFromUrl(announcement.file.toString())
            binding.root.setOnClickListener {
                listener(announcement.id)
            }
        }
    }
}
