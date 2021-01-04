package com.nazar.sobatmasjid.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nazar.sobatmasjid.data.local.entity.*

@Database(
    entities = [
        AnnouncementEntity::class,
        CityEntity::class,
        FinanceEntity::class,
        OfficerEntity::class,
        FollowedMosqueEntity::class,
        FridayPrayerEntity::class,
        LocationEntity::class,
        MosqueEntity::class,
        MosqueDetailEntity::class,
        MosqueRecommendationEntity::class,
        ResearchEntity::class,
        ResearchDetailEntity::class,
        SholatEntity::class
    ], version = 1, exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract val localDao: LocalDao
}