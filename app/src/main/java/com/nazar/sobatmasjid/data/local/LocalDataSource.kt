package com.nazar.sobatmasjid.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.nazar.sobatmasjid.data.local.entity.*
import com.nazar.sobatmasjid.data.local.room.LocalDao

class LocalDataSource private constructor(private val mLocalDao: LocalDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(localDao: LocalDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(localDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getUser() : LiveData<UserEntity> = mLocalDao.getUser()

    fun updateUser(user: UserEntity){
        mLocalDao.updateUser(user)
    }

    fun getMosqueRecommendations() : DataSource.Factory<Int, MosqueRecommendationEntity> = mLocalDao.getMosqueRecommendations()

    fun updateMosqueRecommendations(mosques: List<MosqueRecommendationEntity>){
        mLocalDao.updateMosqueRecommendations(mosques)
    }

    fun getFollowedMosques() : DataSource.Factory<Int, FollowedMosqueEntity> = mLocalDao.getFollowedMosques()

    fun insertFollowedMosques(mosques: List<FollowedMosqueEntity>){
        mLocalDao.insertFollowedMosques(mosques)
    }

    fun getMosques(city: String, name: String) : DataSource.Factory<Int, MosqueEntity> = mLocalDao.getMosques(city, name)

    fun insertMosques(mosques: List<MosqueEntity>){
        mLocalDao.insertMosques(mosques)
    }

    fun getMosqueDetail(id: String) : LiveData<MosqueDetailEntity> = mLocalDao.getMosqueDetail(id)

    fun insertMosqueDetail(mosque: MosqueDetailEntity){
        mLocalDao.insertMosqueDetail(mosque)
    }

    fun getFinancesById(idMosque: String) : DataSource.Factory<Int, FinanceEntity> = mLocalDao.getFinances(idMosque)

    fun insertFinances(finances: List<FinanceEntity>){
        mLocalDao.insertFinances(finances)
    }

    fun getOfficersById(idMosque: String) : DataSource.Factory<Int, OfficerEntity> = mLocalDao.getOfficers(idMosque)

    fun insertOfficers(officers: List<OfficerEntity>){
        mLocalDao.insertOfficers(officers)
    }

    fun getResearches(city: String, title:String) : DataSource.Factory<Int, ResearchEntity> = mLocalDao.getResearches(city, title)

    fun getResearchDetail(id: String) : LiveData<ResearchDetailEntity> = mLocalDao.getResearchDetail(id)

    fun insertResearchDetail(research: ResearchDetailEntity){
        mLocalDao.insertResearchDetail(research)
    }

    fun getResearchesById(idMosque: String) : DataSource.Factory<Int, ResearchEntity> = mLocalDao.getResearchesById(idMosque)

    fun insertResearches(researches: List<ResearchEntity>){
        mLocalDao.insertResearches(researches)
    }

    fun getFridayPrayers(city: String) : DataSource.Factory<Int, FridayPrayerEntity> = mLocalDao.getFridayPrayers(city)

    fun insertFridayPrayers(fridayPrayers: List<FridayPrayerEntity>){
        mLocalDao.insertFridayPrayers(fridayPrayers)
    }

    fun getAnnouncements(city: String, title:String) : DataSource.Factory<Int, AnnouncementEntity> = mLocalDao.getAnnouncements(city, title)

    fun getAnnouncementsById(idMosque: String) : DataSource.Factory<Int, AnnouncementEntity> = mLocalDao.getAnnouncementsById(idMosque)

    fun insertAnnouncement(announcement: List<AnnouncementEntity>){
        mLocalDao.insertAnnouncements(announcement)
    }

    fun getCities() : DataSource.Factory<Int, CityEntity> = mLocalDao.getCities()

    fun insertCities(city: List<CityEntity>){
        mLocalDao.insertCities(city)
    }

    fun getSholatTimes() : DataSource.Factory<Int, SholatEntity> = mLocalDao.getSholatTimes()

    fun insertSholatTimes(sholatTimes: List<SholatEntity>){
        mLocalDao.insertSholatTimes(sholatTimes)
    }
}

