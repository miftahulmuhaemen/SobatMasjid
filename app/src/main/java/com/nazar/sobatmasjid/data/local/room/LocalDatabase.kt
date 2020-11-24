package com.nazar.sobatmasjid.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nazar.sobatmasjid.data.local.entity.*

@Database(
    entities = [
        AnnouncementEntity::class,
        CityEntity::class,
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

    abstract fun localDao(): LocalDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            if (INSTANCE == null) {
                synchronized(LocalDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            LocalDatabase::class.java, "sobatmasjid.db"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE as LocalDatabase
        }
    }

}