//package com.nazar.sobatmasjid.data
//
//import android.util.Log
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import androidx.paging.PagedList
//import com.firebase.ui.auth.data.model.Resource
//import com.nazar.sobatmasjid.api.RetrofitService
//import com.nazar.sobatmasjid.data.remote.ApiResponse
//import com.nazar.sobatmasjid.data.remote.RemoteDataSource
//import com.nazar.sobatmasjid.data.remote.response.UserResponse
//import com.nazar.sobatmasjid.helper.MockResponseFileReader
//import com.nhaarman.mockitokotlin2.verify
//import okhttp3.OkHttpClient
//import okhttp3.mockwebserver.MockResponse
//import okhttp3.mockwebserver.MockWebServer
//import okhttp3.mockwebserver.RecordedRequest
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.junit.runners.JUnit4
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.junit.MockitoJUnitRunner
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//import java.io.File
//import java.sql.Date
//import java.time.LocalDate
//
//@RunWith(MockitoJUnitRunner::class)
//class RemoteDataSourceTest {
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private val server: MockWebServer = MockWebServer()
//    @Mock
//    private lateinit var remoteDataSource : RemoteDataSource
//    @Mock
//    private lateinit var apiService : RetrofitService
//
//    @Mock
//    private val date = Date.valueOf(LocalDate.now().toString())
//
//    @Before
//    fun init() {
//        server.start()
//        apiService = Retrofit.Builder()
//            .baseUrl(server.url("/"))
//            .addConverterFactory(MoshiConverterFactory.create())
//            .client(OkHttpClient())
//            .build()
//            .create(RetrofitService::class.java)
//
//        remoteDataSource = RemoteDataSource(apiService)
//    }
//
//    @After
//    fun shutdown() {
//        server.shutdown()
//    }
//
//    @Test
//    fun `login`(){
//        val responseJson = MockResponseFileReader("login_202.json").content
//        server.enqueue(MockResponse().setBody(responseJson))
//
//        val users = MutableLiveData<ApiResponse<UserResponse>>()
//        Mockito.`when`(remoteDataSource.login(
//            "abc",
//            date,
//            "abc@gmail.com",
//            -3.2850656,
//            114.5954627
//        )).thenReturn(users)
//        verify(remoteDataSource).login(
//            "abc",
//            date,
//            "abc@gmail.com",
//            -3.2850656,
//            114.5954627)
//
//
//    }
//
//
//    private fun takeMockRequest(sut: RetrofitService.() -> Unit): RecordedRequest {
//        return MockWebServer()
//            .use {
//                it.enqueue(MockResponse())
//                it.start()
//                val url = it.url("/")
//
//                sut(retrofitService(url))
//
//                it.takeRequest()
//            }
//    }
//}