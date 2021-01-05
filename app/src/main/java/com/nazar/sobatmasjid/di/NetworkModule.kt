package com.nazar.sobatmasjid.di

import com.google.firebase.messaging.FirebaseMessaging
import com.nazar.sobatmasjid.BuildConfig
import com.nazar.sobatmasjid.api.BasicAuthInterceptor
import com.nazar.sobatmasjid.api.RetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { makeRetrofitService() }
    single { makeOkHttpClient() }
    single { makeLoggingInterceptor() }
    single { FirebaseMessaging.getInstance() }
}

private fun makeRetrofitService(): RetrofitService {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(makeOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .build().create(RetrofitService::class.java)
}

private fun makeOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(makeLoggingInterceptor())
        .addInterceptor(BasicAuthInterceptor(BuildConfig.UN, BuildConfig.PW))
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .build()
}

private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level =
        HttpLoggingInterceptor.Level.BODY
    return logging
}