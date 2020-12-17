package com.nazar.sobatmasjid.ui.fragments.research.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.entity.ResearchDetailEntity
import com.nazar.sobatmasjid.vo.Resource

class ResearchDetailViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getResearchDetail(
        idUser: String,
        idResearch: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<ResearchDetailEntity>> = dataRepository.getResearchDetail(idResearch, idUser, latitude, longitude)

    fun attendResearch(
        idUser: String,
        idResearch: String,
        idMosque: String
    ): LiveData<Boolean> = dataRepository.attendResearch(idUser, idResearch, idMosque)

    fun setAttendResearch(research: ResearchDetailEntity, newState: Boolean) = dataRepository.setAttendResearch(research, newState)
}