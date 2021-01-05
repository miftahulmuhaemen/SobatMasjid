package com.nazar.sobatmasjid.di

import com.nazar.sobatmasjid.preference.Preferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferenceModule = module {
    single { Preferences(androidContext()) }
}
