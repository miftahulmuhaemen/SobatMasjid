package com.nazar.sobatmasjid.ui.fragments.profile.followed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.entity.CityEntity
import com.nazar.sobatmasjid.data.local.entity.FollowedMosqueEntity
import com.nazar.sobatmasjid.vo.Resource

class FollowedMosqueViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun getFollowedMosques(id: String, query: String): LiveData<Resource<PagedList<FollowedMosqueEntity>>> = dataRepository.getFollowedMosques(id, query)
}