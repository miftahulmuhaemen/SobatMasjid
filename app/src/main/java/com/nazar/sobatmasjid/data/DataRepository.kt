package com.nazar.sobatmasjid.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nazar.sobatmasjid.data.local.LocalDataSource
import com.nazar.sobatmasjid.data.local.entity.*
import com.nazar.sobatmasjid.data.remote.ApiResponse
import com.nazar.sobatmasjid.data.remote.RemoteDataSource
import com.nazar.sobatmasjid.data.remote.response.*
import com.nazar.sobatmasjid.utils.AppExecutors
import com.nazar.sobatmasjid.vo.Resource
import java.util.*
import kotlin.collections.ArrayList

class DataRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : DataSource {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(remoteData, localDataSource, appExecutors)
            }
    }

//    override fun postLogin(name: String, date: Date, email: String): LiveData<Resource<BlankResponse>> {
//        return  object : NetworkOutboundResource<BlankResponse>(appExecutors){
//            override fun createCall(): LiveData<ApiResponse<BlankResponse>> = remoteDataSource.postLogin(name, date, email)
//        }.asLiveData()
//    }

    override fun getUser(email: String): LiveData<Resource<UserEntity>> {
        return object : NetworkBoundResource<UserEntity, UserResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<UserEntity> = localDataSource.getUser()

            override fun shouldFetch(data: UserEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<UserResponse>> =
                remoteDataSource.getUser(email)

            override fun saveCallResult(data: UserResponse?) {
                if (data != null) {
                    val user = UserEntity(
                        data.id!!,
                        data.name,
                        data.bornData,
                        data.email,
                        data.gender,
                        data.numberFollow,
                        data.photo,
                        data.motto
                    )
                    localDataSource.updateUser(user)
                }
            }
        }.asLiveData()
    }

    override fun getFollowedMosques(id: Int): LiveData<Resource<PagedList<FollowedMosqueEntity>>> {
        return object :
            NetworkBoundResource<PagedList<FollowedMosqueEntity>, List<FollowedMosqueResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<FollowedMosqueEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getFollowedMosques(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<FollowedMosqueEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<FollowedMosqueResponse>>> =
                remoteDataSource.getFollowedMosque(id)

            override fun saveCallResult(data: List<FollowedMosqueResponse>?) {
                val mosques = ArrayList<FollowedMosqueEntity>()
                for (response in data!!) {
                    val mosque = FollowedMosqueEntity(
                        response.id!!,
                        response.mosqueName,
                        response.photo,
                        response.username
                    )
                    mosques.add(mosque)
                }
                localDataSource.insertFollowedMosques(mosques)
            }
        }.asLiveData()
    }

    override fun getMosqueRecommendations(
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<PagedList<MosqueRecommendationEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MosqueRecommendationEntity>, List<MosqueRecommendationResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<MosqueRecommendationEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getMosqueRecommendations(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<MosqueRecommendationEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MosqueRecommendationResponse>>> =
                remoteDataSource.getMosqueRecommendations(latitude, longitude)

            override fun saveCallResult(data: List<MosqueRecommendationResponse>?) {
                val mosques = ArrayList<MosqueRecommendationEntity>()
                for (response in data!!) {
                    val mosque = MosqueRecommendationEntity(
                        response.id!!,
                        response.name,
                        response.latitude,
                        response.longitude,
                        response.username,
                        response.distance,
                        response.photo
                    )
                    mosques.add(mosque)
                }
                localDataSource.updateMosqueRecommendations(mosques)
            }

        }.asLiveData()
    }

    override fun getMosques(
        latitude: Double,
        longitude: Double,
        city: String,
        name: String
    ): LiveData<Resource<PagedList<MosqueEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MosqueEntity>, List<MosqueResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MosqueEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMosques(city, name), config).build()
            }

            override fun shouldFetch(data: PagedList<MosqueEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MosqueResponse>>> =
                remoteDataSource.getMosques(latitude, longitude)

            override fun saveCallResult(data: List<MosqueResponse>?) {
                val mosques = ArrayList<MosqueEntity>()
                for (response in data!!) {
                    val mosque = MosqueEntity(
                        response.id!!,
                        response.name,
                        response.latitude,
                        response.longitude,
                        response.username,
                        response.distance,
                        response.type,
                        response.classification,
                        response.idCity,
                        response.photo
                    )
                    mosques.add(mosque)
                }
                localDataSource.insertMosques(mosques)
            }

        }.asLiveData()
    }

    override fun getMosqueDetail(
        id: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<MosqueDetailEntity>> {
        return object :
            NetworkBoundResource<MosqueDetailEntity, List<MosqueDetailResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<MosqueDetailEntity> =
                localDataSource.getMosqueDetail(id)

            override fun shouldFetch(data: MosqueDetailEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<MosqueDetailResponse>>> =
                remoteDataSource.getMosqueDetail(id.toInt(), latitude, longitude)

            override fun saveCallResult(data: List<MosqueDetailResponse>?) {
                for (response in data!!) {
                    val researches = ArrayList<ResearchEntity>()
                    val announcements = ArrayList<AnnouncementEntity>()
                    val officers = ArrayList<OfficerEntity>()
                    val finances = ArrayList<FinanceEntity>()

                    val mosque = MosqueDetailEntity(
                        response.id!!,
                        response.name,
                        response.latitude,
                        response.longitude,
                        response.username,
                        response.distance,
                        response.type,
                        response.classification,
                        response.email,
                        response.description,
                        response.standingDate,
                        response.address,
                        response.accountNumber,
                        response.accountName,
                        response.qris,
                        response.bankName,
                        response.urbanVillage,
                        response.subDistrict,
                        response.idCity,
                        response.province,
                        response.photo,
                        response.followed
                    )

                    for (responseResearch in response.research!!) {
                        val research = ResearchEntity(
                            responseResearch.id!!,
                            responseResearch.idMosque,
                            responseResearch.idCity,
                            responseResearch.researchTitle,
                            responseResearch.researchType,
                            responseResearch.date,
                            responseResearch.startTime,
                            responseResearch.endTime,
                            responseResearch.category,
                            responseResearch.ustadzName,
                            responseResearch.ustadzPhoto,
                            responseResearch.mosqueName,
                            responseResearch.mosqueType
                        )
                        researches.add(research)
                    }

                    for (responseAnnouncement in response.announcement!!) {
                        val announcement = AnnouncementEntity(
                            responseAnnouncement.id!!,
                            responseAnnouncement.idMosque,
                            responseAnnouncement.idCity,
                            responseAnnouncement.title,
                            responseAnnouncement.date,
                            responseAnnouncement.category,
                            responseAnnouncement.file,
                            responseAnnouncement.mosqueName,
                            responseAnnouncement.mosqueType
                        )
                        announcements.add(announcement)
                    }

                    for (responseOfficer in response.officer!!) {
                        val officer = OfficerEntity(
                            0,
                            responseOfficer.idMosque,
                            responseOfficer.date,
                            responseOfficer.khatib,
                            responseOfficer.muadzin
                        )
                        officers.add(officer)
                    }

                    for (responseFinance in response.finance!!) {
                        val finance = FinanceEntity(
                            responseFinance.id,
                            responseFinance.idMosque,
                            responseFinance.date,
                            responseFinance.creditIn,
                            responseFinance.creditOut,
                            responseFinance.creditText
                        )
                        finances.add(finance)
                    }

                    localDataSource.insertMosqueDetail(mosque)
                    localDataSource.insertResearches(researches)
                    localDataSource.insertAnnouncement(announcements)
                    localDataSource.insertOfficers(officers)
                    localDataSource.insertFinances(finances)

                }
            }

        }.asLiveData()
    }

    override fun getOfficersById(idMosque: String): LiveData<PagedList<OfficerEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getOfficersById(idMosque), config).build()
    }

    override fun getFinancesById(idMosque: String): LiveData<PagedList<FinanceEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFinancesById(idMosque), config).build()
    }

    override fun getResearches(
        latitude: Double,
        longitude: Double,
        city: String,
        title: String
    ): LiveData<Resource<PagedList<ResearchEntity>>> {
        return object :
            NetworkBoundResource<PagedList<ResearchEntity>, List<ResearchResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<ResearchEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getResearches(city, title),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<ResearchEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResearchResponse>>> =
                remoteDataSource.getResearches(latitude, longitude)

            override fun saveCallResult(data: List<ResearchResponse>?) {
                val researches = ArrayList<ResearchEntity>()
                for (response in data!!) {
                    val research = ResearchEntity(
                        response.id!!,
                        response.idMosque,
                        response.idCity,
                        response.researchTitle,
                        response.researchType,
                        response.date,
                        response.startTime,
                        response.endTime,
                        response.category,
                        response.ustadzName,
                        response.ustadzPhoto,
                        response.mosqueName,
                        response.mosqueType
                    )
                    researches.add(research)
                }
                localDataSource.insertResearches(researches)
            }

        }.asLiveData()
    }

    override fun getResearchDetail(
        id: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Resource<ResearchDetailEntity>> {
        return object :
            NetworkBoundResource<ResearchDetailEntity, List<ResearchDetailResponse>>(appExecutors) {

            override fun loadFromDB(): LiveData<ResearchDetailEntity> =
                localDataSource.getResearchDetail(id)

            override fun shouldFetch(data: ResearchDetailEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<ResearchDetailResponse>>> =
                remoteDataSource.getResearchDetail(id.toInt(), latitude, longitude)

            override fun saveCallResult(data: List<ResearchDetailResponse>?) {
                for (response in data!!) {
                    val research = ResearchDetailEntity(
                        response.id!!,
                        response.idMosque,
                        response.researchTitle,
                        response.researchType,
                        response.researchDate,
                        response.startTime,
                        response.endTime,
                        response.category,
                        response.ustadzName,
                        response.ustadzPhoto,
                        response.mosqueName,
                        response.mosqueType,
                        response.brochure,
                        response.link,
                        response.note,
                        response.followed
                    )
                    localDataSource.insertResearchDetail(research)
                }
            }

        }.asLiveData()
    }

    override fun getResearchesById(idMosque: String): LiveData<PagedList<ResearchEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getResearchesById(idMosque), config).build()
    }

    override fun getFridayPrayers(
        latitude: Double,
        longitude: Double,
        city: String
    ): LiveData<Resource<PagedList<FridayPrayerEntity>>> {
        return object :
            NetworkBoundResource<PagedList<FridayPrayerEntity>, List<FridayPrayerResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<FridayPrayerEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getFridayPrayers(city), config).build()
            }

            override fun shouldFetch(data: PagedList<FridayPrayerEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<FridayPrayerResponse>>> =
                remoteDataSource.getFridayPrayers(latitude, longitude)

            override fun saveCallResult(data: List<FridayPrayerResponse>?) {
                val fridayPrayers = ArrayList<FridayPrayerEntity>()
                for (response in data!!) {
                    val fridayPrayer = FridayPrayerEntity(
                        response.id!!,
                        response.idCity,
                        response.mosqueName,
                        response.mosqueType,
                        response.creditIn,
                        response.creditOut,
                        response.creditText,
                        response.date,
                        response.khatib,
                        response.muadzin
                    )
                    fridayPrayers.add(fridayPrayer)
                }
                localDataSource.insertFridayPrayers(fridayPrayers)
            }

        }.asLiveData()
    }

    override fun getAnnouncements(
        latitude: Double,
        longitude: Double,
        city: String,
        title: String
    ): LiveData<Resource<PagedList<AnnouncementEntity>>> {
        return object :
            NetworkBoundResource<PagedList<AnnouncementEntity>, List<AnnouncementResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<AnnouncementEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getAnnouncements(city, title),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<AnnouncementEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<AnnouncementResponse>>> =
                remoteDataSource.getAnnouncements(latitude, longitude)

            override fun saveCallResult(data: List<AnnouncementResponse>?) {
                val announcements = ArrayList<AnnouncementEntity>()
                for (response in data!!) {
                    val announcement = AnnouncementEntity(
                        response.id!!,
                        response.idMosque,
                        response.idCity,
                        response.title,
                        response.date,
                        response.category,
                        response.file,
                        response.mosqueName,
                        response.mosqueType
                    )
                    announcements.add(announcement)
                }
                localDataSource.insertAnnouncement(announcements)
            }

        }.asLiveData()
    }

    override fun getAnnouncementsById(idMosque: String): LiveData<PagedList<AnnouncementEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAnnouncementsById(idMosque), config).build()
    }

    override fun getCities(): LiveData<Resource<PagedList<CityEntity>>> {
        return object :
            NetworkBoundResource<PagedList<CityEntity>, List<CityResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<CityEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getCities(), config).build()
            }

            override fun shouldFetch(data: PagedList<CityEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CityResponse>>> =
                remoteDataSource.getCities()

            override fun saveCallResult(data: List<CityResponse>?) {
                val cities = ArrayList<CityEntity>()
                for (response in data!!) {
                    val city = CityEntity(
                        response.id!!,
                        response.name,
                        response.latitude,
                        response.longitude,
                        response.apiCode
                    )
                    cities.add(city)
                }
                localDataSource.insertCities(cities)
            }
        }.asLiveData()
    }


    override fun getSholatTimes(
        apiCode: Int,
        dateNow: Date
    ): LiveData<Resource<PagedList<SholatEntity>>> {
        return object :
            NetworkBoundResource<PagedList<SholatEntity>, List<SholatResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<SholatEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getSholatTimes(), config).build()
            }

            override fun shouldFetch(data: PagedList<SholatEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<SholatResponse>>> =
                remoteDataSource.getSholatTimes(apiCode, dateNow)

            override fun saveCallResult(data: List<SholatResponse>?) {
                val sholatTimes = ArrayList<SholatEntity>()
                for (response in data!!) {
                    val sholatTime = SholatEntity(
                        0,
                        response.date,
                        response.imsak,
                        response.shubuh,
                        response.dzuhur,
                        response.ashar,
                        response.maghrib,
                        response.isya
                    )
                    sholatTimes.add(sholatTime)
                }
                localDataSource.insertSholatTimes(sholatTimes)
            }
        }.asLiveData()
    }
}
