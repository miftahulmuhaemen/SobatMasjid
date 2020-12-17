package com.nazar.sobatmasjid.ui.fragments.research.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.entity.ResearchEntity
import com.nazar.sobatmasjid.vo.Resource

class ResearchListViewModel (private val dataRepository: DataRepository) : ViewModel() {
    fun getResearches(
        latitude: Double,
        longitude: Double,
        city: String,
        title: String,
        type: List<String>
    ): LiveData<Resource<PagedList<ResearchEntity>>> = dataRepository.getResearches(latitude, longitude, city, title, type)
}