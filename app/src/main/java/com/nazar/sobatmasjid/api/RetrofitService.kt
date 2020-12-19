package com.nazar.sobatmasjid.api

import com.nazar.sobatmasjid.BuildConfig.API_KEY
import com.nazar.sobatmasjid.BuildConfig.BASE_URL
import com.nazar.sobatmasjid.data.remote.response.*
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface RetrofitService {

    /** USER **/

    @GET("${BASE_URL}user?API-KEY=${API_KEY}")
    suspend fun getUser(
        @Query("email") email: String?
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

    @POST("${BASE_URL}user/{idUser}")
    suspend fun postUpdateProfile(
        @Path("idUser") idUser: Int?,
        @Body requestBody: RequestBody
    ): Response<DefaultResponse>

    /** MOSQUE **/

    @GET("${BASE_URL}mosque/recommendation?API-KEY=${API_KEY}")
    suspend fun getMosqueRecommendations(
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?
    ): Response<MosqueRecommendationListResponse>

    @GET("${BASE_URL}mosque/?API-KEY=${API_KEY}")
    suspend fun getMosques(
        @Query("id-city") idCity: Int?,
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?
    ): Response<MosqueListResponse>

    @GET("${BASE_URL}mosque/detail/{idMosque}?API-KEY=${API_KEY}")
    suspend fun getMosqueDetail(
        @Path("idMosque") idMosque: Int?,
        @Query("id-user") idUser: Int?,
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?
    ): Response<MosqueDetailListResponse>

    @FormUrlEncoded
    @POST("${BASE_URL}mosque/follow")
    suspend fun followMosque(
        @Field("id-user") idUser: Int?,
        @Field("id-mosque") idMosque: Int?,
        @Field("API-KEY") apiKey: String = API_KEY
    ): Response<DefaultResponse>

    @DELETE("${BASE_URL}mosque/unfollow/{idUser}/{idMosque}?API-KEY=${API_KEY}")
    suspend fun unFollowMosque(
        @Path("idUser") idUser: Int?,
        @Path("idMosque") idMosque: Int?
    ): Response<DefaultResponse>

    /** FRIDAY PRAYER **/

    @GET("${BASE_URL}/home/friday_prayer/{idUser}?API-KEY=${API_KEY}")
    suspend fun getFridayPrayers(
        @Path("idUser") idUser: Int?,
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?
    ): Response<FridayPrayerListResponse>

    /** LOCATION **/

    @GET("${BASE_URL}location/city?API-KEY=${API_KEY}")
    suspend fun getCities(): Response<CityListResponse>

    /** SHOLAT **/

    @GET("${BASE_URL}sholat/{apiCode}/{dateNow}?API-KEY=${API_KEY}")
    suspend fun getSholatTimes(
        @Path("apiCode") apiCode: Int?,
        @Path("dateNow") dateNow: Date?
    ): Response<SholatListResponse>

    /** RESEARCH **/

    @GET("${BASE_URL}research/?API-KEY=${API_KEY}")
    suspend fun getResearches(
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?,
        @Query("id-city") idCity: Int?
    ): Response<ResearchListResponse>

    @GET("${BASE_URL}/home/research/{idUser}?API-KEY=${API_KEY}")
    suspend fun getResearchesByUser(
        @Path("idUser") idUser: Int?,
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?
    ): Response<ResearchListResponse>

    @GET("${BASE_URL}research/detail/{idResearch}?API-KEY=${API_KEY}")
    suspend fun getResearchDetail(
        @Path("idResearch") idResearch: Int?,
        @Query("id-user") idUser: Int?,
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?
    ): Response<ResearchDetailListResponse>

    @FormUrlEncoded
    @POST("${BASE_URL}research/attend")
    suspend fun attendResearch(
        @Field("id-user") idUser: Int?,
        @Field("id-research") idResearch: Int?,
        @Field("id-mosque") idMosque: Int?,
        @Field("API-KEY") apiKey: String = API_KEY
    ): Response<DefaultResponse>

    /** ANNOUNCEMENT **/

    @GET("${BASE_URL}announcement/?API-KEY=${API_KEY}")
    suspend fun getAnnouncements(
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?,
        @Query("id-city") idCity: Int?
    ): Response<AnnouncementListResponse>

    @GET("${BASE_URL}/home/announcement/{idUser}?API-KEY=${API_KEY}")
    suspend fun getAnnouncementsByUser(
        @Path("idUser") idUser: Int?,
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?
    ): Response<AnnouncementListResponse>

}