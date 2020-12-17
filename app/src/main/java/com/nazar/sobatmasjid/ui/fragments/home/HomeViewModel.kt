package com.nazar.sobatmasjid.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.entity.*
import com.nazar.sobatmasjid.vo.Resource

class HomeViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun followMosque(idUser: String, idMosque: String): LiveData<Boolean> =
        dataRepository.followMosque(idUser, idMosque)

    fun getMosqueRecommendations(
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<PagedList<MosqueRecommendationEntity>>> =
        dataRepository.getMosqueRecommendations(latitude, longitude)

    fun getMosques(
        latitude: Double,
        longitude: Double,
        idCity: String,
        name: String,
        type: List<String>,
        classification: List<String>
    ): LiveData<Resource<PagedList<MosqueEntity>>> =
        dataRepository.getMosques(latitude, longitude, idCity, name, type, classification)

    fun getResearches(
        idUser: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<PagedList<ResearchEntity>>> =
        dataRepository.getResearchesByUser(idUser, latitude, longitude)

    fun getAnnouncements(
        idUser: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<PagedList<AnnouncementEntity>>> =
        dataRepository.getAnnouncementsByUser(idUser, latitude, longitude)

    fun getFridayPrayers(
        idUser: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<PagedList<FridayPrayerEntity>>> =
        dataRepository.getFridayPrayers(idUser, latitude, longitude)

}