package com.nazar.sobatmasjid.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.nazar.sobatmasjid.data.local.entity.*

@Dao
interface LocalDao {

    /** USER **/

    @Query("SELECT * FROM user")
    fun getUser(): LiveData<UserEntity>

    @Update
    fun updateUser(user: UserEntity)

    @Query("SELECT * FROM followedMosque")
    fun getFollowedMosques(): DataSource.Factory<Int, FollowedMosqueEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFollowedMosques(followedMosques: List<FollowedMosqueEntity>)

    /** MOSQUE **/

    @Query("SELECT * FROM mosqueRecommendation")
    fun getMosqueRecommendations(): DataSource.Factory<Int, MosqueRecommendationEntity>

    @Update
    fun updateMosqueRecommendations(mosqueRecommendations: List<MosqueRecommendationEntity>)

    @Query("SELECT * FROM mosque WHERE city = :city AND name LIKE :name")
    fun getMosques(city: String, name:String): DataSource.Factory<Int, MosqueEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMosques(mosques: List<MosqueEntity>)

    @Query("SELECT * FROM mosqueDetail WHERE id = :id ")
    fun getMosqueDetail(id: String): LiveData<MosqueDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMosqueDetail(mosque: MosqueDetailEntity)

    /** FINANCE **/

    @Query("SELECT * FROM finance WHERE idMosque = :idMosque")
    fun getFinances(idMosque: String): DataSource.Factory<Int, FinanceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFinances(finances: List<FinanceEntity>)

    /** OFFICER **/

    @Query("SELECT * FROM officer WHERE idMosque = :idMosque")
    fun getOfficers(idMosque: String): DataSource.Factory<Int, OfficerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOfficers(researches: List<OfficerEntity>)

    /** RESEARCH **/

    @Query("SELECT * FROM research WHERE idCity = :city AND title LIKE :title")
    fun getResearches(city: String, title:String): DataSource.Factory<Int, ResearchEntity>

    @Query("SELECT * FROM research WHERE idMosque = :idMosque")
    fun getResearchesById(idMosque: String): DataSource.Factory<Int, ResearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResearches(researches: List<ResearchEntity>)

    @Query("SELECT * FROM researchDetail WHERE id = :id ")
    fun getResearchDetail(id: String): LiveData<ResearchDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResearchDetail(research: ResearchDetailEntity)

    /** FRIDAY PRAYER **/

    @Query("SELECT * FROM fridayPrayer WHERE idCity = :city")
    fun getFridayPrayers(city: String): DataSource.Factory<Int, FridayPrayerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFridayPrayers(fridayPrayers: List<FridayPrayerEntity>)

    /** ANNOUNCEMENT **/

    @Query("SELECT * FROM announcement WHERE idCity = :city AND title LIKE :title")
    fun getAnnouncements(city: String, title:String): DataSource.Factory<Int, AnnouncementEntity>

    @Query("SELECT * FROM announcement WHERE idMosque = :idMosque")
    fun getAnnouncementsById(idMosque: String): DataSource.Factory<Int, AnnouncementEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnnouncements(announcements: List<AnnouncementEntity>)

    /** LOCATION **/

    @Query("SELECT * FROM city")
    fun getCities(): DataSource.Factory<Int, CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cities: List<CityEntity>)

    /** SHOLAT **/

    @Query("SELECT * FROM sholat")
    fun getSholatTimes(): DataSource.Factory<Int, SholatEntity>

    @Update
    fun insertSholatTimes(sholatTimes: List<SholatEntity>)

}