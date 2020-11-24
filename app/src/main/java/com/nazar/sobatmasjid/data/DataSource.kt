package com.nazar.sobatmasjid.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.nazar.sobatmasjid.data.local.entity.*
import com.nazar.sobatmasjid.data.remote.ApiResponse
import com.nazar.sobatmasjid.data.remote.response.UserResponse
import com.nazar.sobatmasjid.vo.Resource
import java.util.*

interface DataSource {

    /** USER **/

    fun userLogin(name: String, date: Date, email: String, latitude: Double, longitude: Double): LiveData<ApiResponse<UserResponse>>

    /** MOSQUE **/

    fun getMosqueRecommendations(latitude: Double, longitude: Double) : LiveData<Resource<PagedList<MosqueRecommendationEntity>>>

    fun getFollowedMosques(id: Int) : LiveData<Resource<PagedList<FollowedMosqueEntity>>>

    fun getMosques(latitude: Double, longitude: Double, idCity: String, name: String) : LiveData<Resource<PagedList<MosqueEntity>>>

    fun getMosqueDetail(idUser: String, idMosque: String, latitude: Double, longitude: Double) : LiveData<Resource<MosqueDetailEntity>>

    fun getOfficersById(idMosque: String): LiveData<PagedList<OfficerEntity>>

    fun getFinancesById(idMosque: String): LiveData<PagedList<FinanceEntity>>

    fun setFollowMosque(mosque: MosqueDetailEntity, newState: Boolean)

    fun followMosque(idUser: String, idMosque: String): LiveData<Boolean>

    fun unFollowMosque(idUser: String, idMosque: String): LiveData<Boolean>

    /** RESEARCH **/

    fun getResearches(latitude: Double, longitude: Double, city: String, title: String) : LiveData<Resource<PagedList<ResearchEntity>>>

    fun getResearchDetail(idUser: String, idResearch: String, latitude: Double, longitude: Double) : LiveData<Resource<ResearchDetailEntity>>

    fun getResearchesById(idMosque: String): LiveData<PagedList<ResearchEntity>>

    fun attendResearch(idUser: String, idResearch: String, idMosque: String): LiveData<Boolean>

    fun setAttendResearch(research: ResearchDetailEntity, newState: Boolean)

    /** FRIDAY PRAYER **/

    fun getFridayPrayers(latitude: Double, longitude: Double, city: String) : LiveData<Resource<PagedList<FridayPrayerEntity>>>

    /** ANNOUNCEMENT **/

    fun getAnnouncements(latitude: Double, longitude: Double, city: String, title: String) : LiveData<Resource<PagedList<AnnouncementEntity>>>

    fun getAnnouncementsById(idMosque: String): LiveData<PagedList<AnnouncementEntity>>

    /** CITY **/

    fun getCities() : LiveData<Resource<PagedList<CityEntity>>>

    /** SHOLAT **/

    fun getSholatTimes(apiCode: Int, dateNow: Date) : LiveData<Resource<PagedList<SholatEntity>>>
}