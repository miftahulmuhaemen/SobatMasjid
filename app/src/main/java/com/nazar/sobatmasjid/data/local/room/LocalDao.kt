package com.nazar.sobatmasjid.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.nazar.sobatmasjid.data.local.entity.*

@Dao
interface LocalDao {

    /** USER **/

    @Query("SELECT * FROM followedMosque")
    fun getFollowedMosques(): DataSource.Factory<Int, FollowedMosqueEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFollowedMosques(followedMosques: List<FollowedMosqueEntity>)

    /** MOSQUE **/

    @Query("SELECT * FROM mosqueRecommendation")
    fun getMosqueRecommendations(): DataSource.Factory<Int, MosqueRecommendationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateMosqueRecommendations(mosqueRecommendations: List<MosqueRecommendationEntity>)

    @Query("SELECT * FROM mosque WHERE idCity = :idCity AND name LIKE '%' || :name || '%' AND type IN(:type) AND classification IN(:classification)")
    fun getMosques(idCity: String, name:String, type: List<String>, classification: List<String>): DataSource.Factory<Int, MosqueEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMosques(mosques: List<MosqueEntity>)

    @Query("SELECT * FROM mosqueDetail WHERE id = :id ")
    fun getMosqueDetail(id: String): LiveData<MosqueDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMosqueDetail(mosque: MosqueDetailEntity)

    @Update
    fun setFollowMosque(mosque: MosqueDetailEntity)

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

    @Query("SELECT * FROM research WHERE idCity = :city AND title LIKE '%' || :title || '%' AND researchType IN(:type)")
    fun getResearches(city: String, title:String, type: List<String>): DataSource.Factory<Int, ResearchEntity>

    @Query("SELECT * FROM research WHERE isFollowedMosque = 1")
    fun getResearchesByUser(): DataSource.Factory<Int, ResearchEntity>

    @Query("SELECT * FROM research WHERE idMosque = :idMosque")
    fun getResearchesById(idMosque: String): DataSource.Factory<Int, ResearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResearches(researches: List<ResearchEntity>)

    @Query("SELECT * FROM researchDetail WHERE id = :id ")
    fun getResearchDetail(id: String): LiveData<ResearchDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResearchDetail(research: ResearchDetailEntity)

    @Update
    fun setAttendResearch(research: ResearchDetailEntity)

    /** FRIDAY PRAYER **/

    @Query("SELECT * FROM fridayPrayer")
    fun getFridayPrayers(): DataSource.Factory<Int, FridayPrayerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFridayPrayers(fridayPrayers: List<FridayPrayerEntity>)

    /** ANNOUNCEMENT **/

    @Query("SELECT * FROM announcement WHERE idCity = :city AND title LIKE '%' || :title || '%' AND category IN(:category) ")
    fun getAnnouncements(city: String, title:String, category: List<String>): DataSource.Factory<Int, AnnouncementEntity>

    @Query("SELECT * FROM announcement WHERE isFollowedMosque = 1")
    fun getAnnouncementsByUser(): DataSource.Factory<Int, AnnouncementEntity>

    @Query("SELECT * FROM announcement WHERE idMosque = :idMosque")
    fun getAnnouncementsByIdMosque(idMosque: String): DataSource.Factory<Int, AnnouncementEntity>

    @Query("SELECT * FROM announcement WHERE id = :id")
    fun getAnnouncementByIdAnnouncement(id: String): LiveData<AnnouncementEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnnouncements(announcements: List<AnnouncementEntity>)

    /** LOCATION **/

    @Query("SELECT * FROM city WHERE name LIKE '%' || :query || '%' ")
    fun getCities(query: String): DataSource.Factory<Int, CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cities: List<CityEntity>)

    /** SHOLAT **/

    @Query("SELECT * FROM sholat")
    fun getSholatTimes(): DataSource.Factory<Int, SholatEntity>

    @Update
    fun insertSholatTimes(sholatTimes: List<SholatEntity>)

}