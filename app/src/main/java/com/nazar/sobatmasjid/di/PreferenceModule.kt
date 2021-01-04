package com.nazar.sobatmasjid.di

import com.nazar.sobatmasjid.BuildConfig
import com.nazar.sobatmasjid.api.BasicAuthInterceptor
import com.nazar.sobatmasjid.api.RetrofitService
import com.nazar.sobatmasjid.preference.Preferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val preferenceModule = module {
    single { Preferences(androidContext()) }
}
