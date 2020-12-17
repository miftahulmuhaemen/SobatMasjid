package com.nazar.sobatmasjid.ui.fragments.announcement.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.entity.AnnouncementEntity
import com.nazar.sobatmasjid.data.local.entity.ResearchEntity
import com.nazar.sobatmasjid.vo.Resource

class AnnouncementListViewModel (private val dataRepository: DataRepository) : ViewModel() {
    fun getAnnouncements(
        latitude: Double,
        longitude: Double,
        idCity: String,
        title: String,
        category: List<String>
    ): LiveData<Resource<PagedList<AnnouncementEntity>>> = dataRepository.getAnnouncements(latitude, longitude, idCity, title, category)
}