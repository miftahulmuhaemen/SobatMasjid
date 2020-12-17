package com.nazar.sobatmasjid.ui.fragments.adhan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.entity.SholatEntity
import com.nazar.sobatmasjid.vo.Resource
import java.util.*

class AdhanViewModel (private val dataRepository: DataRepository) : ViewModel() {
    fun getSholatTimes(
        apiCode: String,
        dateNow: Date
    ): LiveData<Resource<SholatEntity>> = dataRepository.getSholatTimes(apiCode.toInt(), dateNow)
}