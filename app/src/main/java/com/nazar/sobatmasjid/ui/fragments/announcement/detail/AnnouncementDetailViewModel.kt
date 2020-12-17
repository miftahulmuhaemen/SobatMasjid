package com.nazar.sobatmasjid.ui.fragments.announcement.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.entity.AnnouncementEntity
import com.nazar.sobatmasjid.data.local.entity.ResearchDetailEntity
import com.nazar.sobatmasjid.vo.Resource

class AnnouncementDetailViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun getAnnouncementByIdAnnouncement(id: String): LiveData<AnnouncementEntity> = dataRepository.getAnnouncementByIdAnnouncement(id)
}