package com.nazar.sobatmasjid.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.nazar.sobatmasjid.data.local.entity.*
import com.nazar.sobatmasjid.data.remote.ApiResponse
import com.nazar.sobatmasjid.data.remote.response.UserResponse
import com.nazar.sobatmasjid.vo.Resource
import okhttp3.RequestBody
import java.util.*

interface DataSource {

    /** USER **/

    fun userLogin(
        name: String,
        date: Date,
        email: String,
        latitude: Double,
        longitude: Double
    ): LiveData<ApiResponse<UserResponse>>

    fun updateUser(
        idUser: String,
        requestBody: RequestBody
    ): LiveData<ApiResponse<UserResponse>>

    /** MOSQUE **/

    fun getMosqueRecommendations(
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<PagedList<MosqueRecommendationEntity>>>

    fun getFollowedMosques(
        id: String,
        name: String
    ): LiveData<Resource<PagedList<FollowedMosqueEntity>>>

    fun getMosques(
        latitude: Double,
        longitude: Double,
        idCity: String,
        name: String,
        type: List<String>,
        classification: List<String>
    ): LiveData<Resource<PagedList<MosqueEntity>>>

    fun getMosqueDetail(
        idMosque: String,
        idUser: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<MosqueDetailEntity>>

    fun getOfficersById(idMosque: String): LiveData<PagedList<OfficerEntity>>

    fun getFinancesById(idMosque: String): LiveData<PagedList<FinanceEntity>>

    fun setFollowMosque(mosque: MosqueDetailEntity, newState: Boolean)

    fun followMosque(idUser: String, idMosque: String): LiveData<Boolean>

    fun unFollowMosque(idUser: String, idMosque: String): LiveData<Boolean>

    /** RESEARCH **/

    fun getResearches(
        latitude: Double,
        longitude: Double,
        city: String,
        title: String,
        type: List<String>
    ): LiveData<Resource<PagedList<ResearchEntity>>>

    fun getResearchesByUser(
        idUser: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<PagedList<ResearchEntity>>>

    fun getResearchDetail(
        idUser: String,
        idResearch: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<ResearchDetailEntity>>

    fun getResearchesById(idMosque: String): LiveData<PagedList<ResearchEntity>>

    fun attendResearch(idUser: String, idResearch: String, idMosque: String): LiveData<Boolean>

    fun setAttendResearch(research: ResearchDetailEntity, newState: Boolean)

    /** FRIDAY PRAYER **/

    fun getFridayPrayers(
        idUser: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<PagedList<FridayPrayerEntity>>>

    /** ANNOUNCEMENT **/

    fun getAnnouncements(
        latitude: Double,
        longitude: Double,
        idCity: String,
        title: String,
        category: List<String>
    ): LiveData<Resource<PagedList<AnnouncementEntity>>>

    fun getAnnouncementsByUser(
        idUser: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<PagedList<AnnouncementEntity>>>

    fun getAnnouncementsByIdMosque(idMosque: String): LiveData<PagedList<AnnouncementEntity>>

    fun getAnnouncementByIdAnnouncement(id: String): LiveData<AnnouncementEntity>

    /** CITY **/

    fun getCities(query: String): LiveData<Resource<PagedList<CityEntity>>>

    /** SHOLAT **/

    fun getSholatTimes(apiCode: Int, dateNow: Date): LiveData<Resource<SholatEntity>>
}