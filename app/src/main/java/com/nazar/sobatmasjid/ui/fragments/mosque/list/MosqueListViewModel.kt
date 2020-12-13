package com.nazar.sobatmasjid.ui.fragments.mosque.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.entity.MosqueEntity
import com.nazar.sobatmasjid.vo.Resource

class MosqueListViewModel (private val dataRepository: DataRepository) : ViewModel() {
    fun getMosques(
        latitude: Double,
        longitude: Double,
        idCity: String,
        name: String,
        type: List<String>,
        classification: List<String>
    ): LiveData<Resource<PagedList<MosqueEntity>>> =
        dataRepository.getMosques(latitude, longitude, idCity, name, type, classification)
}