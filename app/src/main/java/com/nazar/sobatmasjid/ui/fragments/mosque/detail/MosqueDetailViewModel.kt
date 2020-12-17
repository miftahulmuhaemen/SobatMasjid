package com.nazar.sobatmasjid.ui.fragments.mosque.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.entity.*
import com.nazar.sobatmasjid.vo.Resource

class MosqueDetailViewModel(private val dataRepository: DataRepository) : ViewModel() {

    var mosque: MutableLiveData<MosqueDetailEntity> = MutableLiveData()

    fun setMosque(mosque: MosqueDetailEntity){
        this.mosque.value = mosque
    }

    fun getMosqueDetail(
        idUser: String,
        idMosque: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<MosqueDetailEntity>> = dataRepository.getMosqueDetail(idUser, idMosque, latitude, longitude)

    fun followMosque(idUser: String, idMosque: String): LiveData<Boolean> = dataRepository.followMosque(idUser, idMosque)

    fun unFollowMosque(idUser: String, idMosque: String): LiveData<Boolean> = dataRepository.unFollowMosque(idUser, idMosque)

    fun setFollowMosque(mosque: MosqueDetailEntity, newState: Boolean) = dataRepository.setFollowMosque(mosque, newState)

    fun getResearchesById(idMosque: String): LiveData<PagedList<ResearchEntity>> = dataRepository.getResearchesById(idMosque)

    fun getAnnouncementsById(idMosque: String): LiveData<PagedList<AnnouncementEntity>> = dataRepository.getAnnouncementsByIdMosque(idMosque)

    fun getOfficersById(idMosque: String): LiveData<PagedList<OfficerEntity>> = dataRepository.getOfficersById(idMosque)

    fun getFinancesById(idMosque: String): LiveData<PagedList<FinanceEntity>> = dataRepository.getFinancesById(idMosque)

}