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
    suspend fun postLogin(
        @Field("name") name: String?,
        @Field("born-date") date: Date?,
        @Field("email") email: String?,
        @Field("API-KEY") apiKey: String = API_KEY
    ): Response<BlankResponse>

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

    @GET("${BASE_URL}mosque/classification?API-KEY=${API_KEY}&latitude={latitude}&longitude={longitude}")
    suspend fun getMosques(
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?
    ): Response<MosqueListResponse>

    @GET("${BASE_URL}mosque/detail/{idMosque}?API-KEY=${API_KEY}")
    suspend fun getMosqueDetail(
        @Path("idMosque") idMosque: Int?,
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?
    ): Response<MosqueDetailListResponse>

    @FormUrlEncoded
    @POST("${BASE_URL}mosque/follow")
    suspend fun postFollowMosque(
        @Field("id-mosque") idMosque: Int?,
        @Field("id-user") idUser: Int?,
        @Field("API-KEY") apiKey: String = API_KEY
    )

    @DELETE("${BASE_URL}mosque/unfollow/{idUser}/{idMosque}?API-KEY=${API_KEY}")
    suspend fun deleteUnfollowMosque(
        @Path("idUser") idUser: Int?,
        @Path("idMosque") idMosque: Int?
    )

    /** FRIDAY PRAYER **/

    @GET("${BASE_URL}location/fridayprayer?API-KEY=${API_KEY}")
    suspend fun getFridayPrayers(
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

    /** HOME **/

    @GET("${BASE_URL}home/{idUser}?API-KEY=${API_KEY}&latitude={latitude}&longitude={longitude}")
    suspend fun getHome(
        @Path("idUser") idUser: Int?,
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?
    ): Response<HomeListResponse>

    /** RESEARCH **/

    @GET("${BASE_URL}research/?API-KEY=${API_KEY}&latitude={latitude}&longitude={longitude}")
    suspend fun getResearches(
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?
    ): Response<ResearchListResponse>

    @GET("${BASE_URL}research/detail/{idResearch}?API-KEY=${API_KEY}&latitude={latitude}&longitude={longitude}")
    suspend fun getResearchDetail(
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

    @GET("${BASE_URL}announcement/?API-KEY=${API_KEY}&latitude={latitude}&longitude={longitude}")
    suspend fun getAnnouncements(
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?
    ): Response<AnnouncementListResponse>

    @GET("${BASE_URL}announcement/detail/{idAnnouncement}?API-KEY=${API_KEY}")
    suspend fun getAnnouncementDetail(
        @Path("idAnnouncement") idResearch: Int?
    ): Response<AnnouncementDetailListResponse>

}