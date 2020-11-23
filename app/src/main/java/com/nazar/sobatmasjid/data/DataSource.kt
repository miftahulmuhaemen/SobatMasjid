package com.nazar.sobatmasjid.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.nazar.sobatmasjid.data.local.entity.*
import com.nazar.sobatmasjid.vo.Resource
import java.util.*

interface DataSource {

    fun followMosque(id: String, idMosque: String): LiveData<Boolean>

    fun unFollowMosque(id: String, idMosque: String): LiveData<Boolean>



    fun getUser(email: String) : LiveData<Resource<UserEntity>>

    fun getMosqueRecommendations(latitude: Double, longitude: Double) : LiveData<Resource<PagedList<MosqueRecommendationEntity>>>

    fun getFollowedMosques(id: Int) : LiveData<Resource<PagedList<FollowedMosqueEntity>>>

    fun getMosques(latitude: Double, longitude: Double, city: String, name: String) : LiveData<Resource<PagedList<MosqueEntity>>>

    fun getMosqueDetail(id: String, latitude: Double, longitude: Double) : LiveData<Resource<MosqueDetailEntity>>

    fun getResearches(latitude: Double, longitude: Double, city: String, title: String) : LiveData<Resource<PagedList<ResearchEntity>>>

    fun getResearchDetail(id: String, latitude: Double, longitude: Double) : LiveData<Resource<ResearchDetailEntity>>

    fun getResearchesById(idMosque: String): LiveData<PagedList<ResearchEntity>>

    fun getFridayPrayers(latitude: Double, longitude: Double, city: String) : LiveData<Resource<PagedList<FridayPrayerEntity>>>

    fun getAnnouncements(latitude: Double, longitude: Double, city: String, title: String) : LiveData<Resource<PagedList<AnnouncementEntity>>>

    fun getAnnouncementsById(idMosque: String): LiveData<PagedList<AnnouncementEntity>>

    fun getCities() : LiveData<Resource<PagedList<CityEntity>>>

    fun getSholatTimes(apiCode: Int, dateNow: Date) : LiveData<Resource<PagedList<SholatEntity>>>

    fun getOfficersById(idMosque: String): LiveData<PagedList<OfficerEntity>>

    fun getFinancesById(idMosque: String): LiveData<PagedList<FinanceEntity>>
}