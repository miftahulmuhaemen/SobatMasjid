package com.nazar.sobatmasjid.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.nazar.sobatmasjid.data.local.entity.*
import com.nazar.sobatmasjid.data.local.room.LocalDao

class LocalDataSource (private val mLocalDao: LocalDao){

    /** MOSQUE **/

    fun getMosqueRecommendations() : DataSource.Factory<Int, MosqueRecommendationEntity> = mLocalDao.getMosqueRecommendations()

    fun updateMosqueRecommendations(mosques: List<MosqueRecommendationEntity>){
        mLocalDao.updateMosqueRecommendations(mosques)
    }

    fun getFollowedMosques(name: String) : DataSource.Factory<Int, FollowedMosqueEntity> = mLocalDao.getFollowedMosques(name)

    fun insertFollowedMosques(mosques: List<FollowedMosqueEntity>){
        mLocalDao.insertFollowedMosques(mosques)
    }

    fun getMosques(idCity: String, name: String, type: List<String>, classification: List<String>) : DataSource.Factory<Int, MosqueEntity> = mLocalDao.getMosques(idCity, name, type, classification)

    fun insertMosques(mosques: List<MosqueEntity>){
        mLocalDao.insertMosques(mosques)
    }

    fun getMosqueDetail(id: String) : LiveData<MosqueDetailEntity> = mLocalDao.getMosqueDetail(id)

    fun insertMosqueDetail(mosque: MosqueDetailEntity){
        mLocalDao.insertMosqueDetail(mosque)
    }

    fun setFollowMosque(mosque: MosqueDetailEntity, newState: Boolean){
        mosque.followed = newState
        mLocalDao.setFollowMosque(mosque)
    }

    fun getFinancesById(idMosque: String) : DataSource.Factory<Int, FinanceEntity> = mLocalDao.getFinances(idMosque)

    fun insertFinances(finances: List<FinanceEntity>){
        mLocalDao.insertFinances(finances)
    }

    fun getOfficersById(idMosque: String) : DataSource.Factory<Int, OfficerEntity> = mLocalDao.getOfficers(idMosque)

    fun insertOfficers(officers: List<OfficerEntity>){
        mLocalDao.insertOfficers(officers)
    }

    /** RESEARCH **/

    fun getResearches(city: String, title:String, type: List<String>) : DataSource.Factory<Int, ResearchEntity> = mLocalDao.getResearches(city, title, type)

    fun getResearchesByUser() : DataSource.Factory<Int, ResearchEntity> = mLocalDao.getResearchesByUser()

    fun getResearchDetail(id: String) : LiveData<ResearchDetailEntity> = mLocalDao.getResearchDetail(id)

    fun setAttendResearch(research: ResearchDetailEntity, newState: Boolean){
        research.attend = newState
        mLocalDao.setAttendResearch(research)
    }

    fun insertResearchDetail(research: ResearchDetailEntity){
        mLocalDao.insertResearchDetail(research)
    }

    fun getResearchesById(idMosque: String) : DataSource.Factory<Int, ResearchEntity> = mLocalDao.getResearchesById(idMosque)

    fun insertResearches(researches: List<ResearchEntity>){
        mLocalDao.insertResearches(researches)
    }

    /** FRIDAY PRAYER **/

    fun getFridayPrayers() : DataSource.Factory<Int, FridayPrayerEntity> = mLocalDao.getFridayPrayers()

    fun insertFridayPrayers(fridayPrayers: List<FridayPrayerEntity>){
        mLocalDao.insertFridayPrayers(fridayPrayers)
    }

    /** ANNOUNCEMENT **/

    fun getAnnouncements(city: String, title:String, category: List<String>) : DataSource.Factory<Int, AnnouncementEntity> = mLocalDao.getAnnouncements(city, title, category)

    fun getAnnouncementsByUser() : DataSource.Factory<Int, AnnouncementEntity> = mLocalDao.getAnnouncementsByUser()

    fun getAnnouncementsByIdMosque(idMosque: String) : DataSource.Factory<Int, AnnouncementEntity> = mLocalDao.getAnnouncementsByIdMosque(idMosque)

    fun getAnnouncementsByIdAnnouncement(id: String) : LiveData<AnnouncementEntity> = mLocalDao.getAnnouncementByIdAnnouncement(id)

    fun insertAnnouncement(announcement: List<AnnouncementEntity>){
        mLocalDao.insertAnnouncements(announcement)
    }

    /** CITY **/

    fun getCities(query: String) : DataSource.Factory<Int, CityEntity> = mLocalDao.getCities(query)

    fun insertCities(city: List<CityEntity>){
        mLocalDao.insertCities(city)
    }

    /** SHOLAT **/

    fun getSholatTimes() : LiveData<SholatEntity> = mLocalDao.getSholatTimes()

    fun insertSholatTimes(sholatTimes: List<SholatEntity>){
        mLocalDao.insertSholatTimes(sholatTimes)
    }
}

