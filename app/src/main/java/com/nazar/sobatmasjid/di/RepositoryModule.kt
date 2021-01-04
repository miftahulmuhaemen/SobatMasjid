package com.nazar.sobatmasjid.di
import android.app.Application
import androidx.room.Room
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.LocalDataSource
import com.nazar.sobatmasjid.data.local.room.LocalDao
import com.nazar.sobatmasjid.data.local.room.LocalDatabase
import com.nazar.sobatmasjid.data.remote.RemoteDataSource
import com.nazar.sobatmasjid.utils.AppExecutors
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {

    fun provideDatabase(application: Application): LocalDatabase {
        return Room.databaseBuilder(application, LocalDatabase::class.java, "localdatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideLocalDao(database: LocalDatabase): LocalDao {
        return  database.localDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideLocalDao(get()) }
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { AppExecutors() }
    single { DataRepository(get(), get(), get()) }
}