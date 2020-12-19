package com.nazar.sobatmasjid.ui.fragments.announcement.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.entity.AnnouncementEntity

class AnnouncementDetailViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun getAnnouncementByIdAnnouncement(id: String): LiveData<AnnouncementEntity> = dataRepository.getAnnouncementByIdAnnouncement(id)
}