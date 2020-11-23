package com.nazar.sobatmasjid.api

import com.nazar.sobatmasjid.BuildConfig.API_KEY
import com.nazar.sobatmasjid.BuildConfig.BASE_URL
import com.nazar.sobatmasjid.data.remote.response.*
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface RetrofitService {

    /** USER **/

    @GET("${BASE_URL}user?API-KEY=${API_KEY}&email={email}")
    suspend fun getUser(
        @Path("email") email: String?
    ): Response<UserListResponse>

    @GET("${BASE_URL}user/follow/{idUser}?API-KEY=${API_KEY}")
    suspend fun getFollowedMosques(
        @Path("idUser") idUser: Int?
    ): Response<FollowedMosqueListResponse>

    @FormUrlEncoded
    @POST("${BASE_URL}user/login")
    suspend fun login(
        @Field("name") name: String?,
        @Field("born-date") date: Date?,
        @Field("email") email: String?,
        @Field("latitude") latitude: Double?,
        @Field("longitude") longitude: Double?,
        @Field("API-KEY") apiKey: String = API_KEY
    ): Response<UserListResponse>

//    @FormUrlEncoded
//    @POST("${BASE_URL}user/{idUser}")
//    suspend fun postUpdateProfile(
//        @Path("idUser") idUser: Int?,
//        @Field("name") idResearch: Int?,
//        @Field("born-date") idMosque: Int?,
//        @Field("email") idUser: Int?,
//        @Field("gender") idUser: Int?,
//        @Field("photo") idUser: Int?,
//        @Field("motto") idUser: String?
//    )

    /** MOSQUE **/

    @GET("${BASE_URL}mosque/recommendation?API-KEY=${API_KEY}&latitude={latitude}&longitude={longitude}")
    suspend fun getMosqueRecommendations(
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?
    ): Response<MosqueRecommendationListResponse>

    @GET("${BASE_URL}mosque/?API-KEY=${API_KEY}&latitude={latitude}&longitude={longitude}&id-city={idCity}")
    suspend fun getMosques(
        @Path("idCity") idCity: Int?,
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?
    ): Response<MosqueListResponse>

    @GET("${BASE_URL}mosque/detail/{idMosque}?API-KEY=${API_KEY}&longitude={longitude}&id-user={idUser}")
    suspend fun getMosqueDetail(
        @Path("idUser") idUser: Int?,
        @Path("idMosque") idMosque: Int?,
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?
    ): Response<MosqueDetailListResponse>

    @FormUrlEncoded
    @POST("${BASE_URL}mosque/follow")
    suspend fun followMosque(
        @Field("id-mosque") idMosque: Int?,
        @Field("id-user") idUser: Int?,
        @Field("API-KEY") apiKey: String = API_KEY
    ): Response<DefaultResponse>

    @DELETE("${BASE_URL}mosque/unfollow/{idUser}/{idMosque}?API-KEY=${API_KEY}")
    suspend fun unFollowMosque(
        @Path("idUser") idUser: Int?,
        @Path("idMosque") idMosque: Int?
    ): Response<DefaultResponse>

    /** FRIDAY PRAYER **/

    @GET("${BASE_URL}/friday_prayer/{idUser}?API-KEY=${API_KEY}&latitude={latitude}&longitude={longitude}")
    suspend fun getFridayPrayers(
        @Path("idUser") idUser: Int?,
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?
    ): Response<FridayPrayerListResponse>

    /** LOCATION **/

    @GET("${BASE_URL}location/city?API-KEY=${API_KEY}")
    suspend fun getCities(): Response<CityListResponse>

    /** SHOLAT **/

    @GET("${BASE_URL}sholat/{apiCode}/{dateNow}?API-KEY=${API_KEY}")
    suspend fun getSholatTimes(
        @Field("apiCode") apiCode: Int?,
        @Field("dateNow") dateNow: Date?
    ): Response<SholatListResponse>

    /** RESEARCH **/

    @GET("${BASE_URL}research/?API-KEY=${API_KEY}&latitude={latitude}&longitude={longitude}&id-city={idCity}")
    suspend fun getResearches(
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?,
        @Path("idCity") idCity: Int?
    ): Response<ResearchListResponse>

    @GET("${BASE_URL}research/detail/{idResearch}?API-KEY=${API_KEY}&latitude={latitude}&longitude={longitude}&id-user={idUser}")
    suspend fun getResearchDetail(
        @Field("idUser") idUser: Int?,
        @Path("idResearch") idResearch: Int?,
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?
    ): Response<ResearchDetailListResponse>

    @FormUrlEncoded
    @POST("${BASE_URL}research/attend")
    suspend fun postAttendResearch(
        @Field("id-research") idResearch: Int?,
        @Field("id-mosque") idMosque: Int?,
        @Field("id-user") idUser: Int?,
        @Field("API-KEY") apiKey: String = API_KEY
    )

    /** ANNOUNCEMENT **/

    @GET("${BASE_URL}announcement/?API-KEY=${API_KEY}&latitude={latitude}&longitude={longitude}&id-city={idCity}")
    suspend fun getAnnouncements(
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?,
        @Path("idCity") idCity: Int?
    ): Response<AnnouncementListResponse>

    @GET("${BASE_URL}announcement/detail/{idAnnouncement}?API-KEY=${API_KEY}&latitude={latitude}&longitude={longitude}")
    suspend fun getAnnouncementDetail(
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?,
        @Path("idAnnouncement") idAnnouncement: Int?
    ): Response<AnnouncementDetailListResponse>

}