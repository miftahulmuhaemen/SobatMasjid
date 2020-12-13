package com.nazar.sobatmasjid.ui.fragments.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.entity.CityEntity
import com.nazar.sobatmasjid.vo.Resource

class LocationViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun getCities(query: String): LiveData<Resource<PagedList<CityEntity>>> = dataRepository.getCities(query)
}