package com.nazar.sobatmasjid.di

import android.content.Context
import com.nazar.sobatmasjid.api.RetrofitFactory
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.LocalDataSource
import com.nazar.sobatmasjid.data.local.room.LocalDatabase
import com.nazar.sobatmasjid.data.remote.RemoteDataSource
import com.nazar.sobatmasjid.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): DataRepository {

        val database = LocalDatabase.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.localDao())
        val appExecutors = AppExecutors()
        val remoteRepository = RemoteDataSource.getInstance(RetrofitFactory.makeRetrofitService())

        return DataRepository.getInstance(remoteRepository,localDataSource,appExecutors)
    }
}
