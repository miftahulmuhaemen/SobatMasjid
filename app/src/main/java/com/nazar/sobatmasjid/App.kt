package com.nazar.sobatmasjid

import android.app.Application
import com.nazar.sobatmasjid.di.viewModelModule
import com.nazar.sobatmasjid.di.networkModule
import com.nazar.sobatmasjid.di.preferenceModule
import com.nazar.sobatmasjid.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule, viewModelModule, preferenceModule))
        }
    }
}
