package com.nazar.sobatmasjid.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nazar.sobatmasjid.api.RetrofitService
import com.nazar.sobatmasjid.data.remote.response.*
import com.nazar.sobatmasjid.utils.EspressoIdlingResource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.*

class RemoteDataSource private constructor(private val service: RetrofitService) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: RetrofitService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    /** USER **/

    fun login(name: String, date: Date, email: String, latitude: Double, longitude: Double) : LiveData<ApiResponse<UserResponse>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<UserResponse>>()
        GlobalScope.launch {
            val response = service.login(name, date, email, latitude, longitude)
            val responseBody = response.body()?.data?.first()
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.error(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }

    fun updateUser(
        idUser: Int,
        requestBody: RequestBody
    ) : LiveData<ApiResponse<Boolean>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<Boolean>>()
        GlobalScope.launch {
            val response = service.postUpdateProfile(idUser, requestBody)
            val responseBody = response.body()?.status
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.error(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }

    fun getFollowedMosque(idUser: Int) : LiveData<ApiResponse<List<FollowedMosqueResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<FollowedMosqueResponse>>>()
        GlobalScope.launch {
            val response = service.getFollowedMosques(idUser)
            val responseBody = response.body()?.data
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.empty(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }

    /** MOSQUE **/

    fun getMosqueRecommendations(latitude: Double, longitude: Double) : LiveData<ApiResponse<List<MosqueRecommendationResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MosqueRecommendationResponse>>>()
        GlobalScope.launch {
            val response = service.getMosqueRecommendations(latitude, longitude)
            val responseBody = response.body()?.data
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.error(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }

    fun getMosques(idCity:Int, latitude: Double, longitude: Double) : LiveData<ApiResponse<List<MosqueResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MosqueResponse>>>()
        GlobalScope.launch {
            val response = service.getMosques(idCity, latitude, longitude)
            val responseBody = response.body()?.mosque
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.empty(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }

    fun getMosqueDetail(idMosque:Int, idUser:Int, latitude: Double, longitude: Double) : LiveData<ApiResponse<List<MosqueDetailResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MosqueDetailResponse>>>()
        GlobalScope.launch {
            val response = service.getMosqueDetail(idMosque, idUser, latitude, longitude)
            val responseBody = response.body()?.mosque
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.empty(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }

    fun followMosque(id: Int, idMosque: Int) : LiveData<Boolean> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<Boolean>()
        GlobalScope.launch {
            val response = service.followMosque(id, idMosque)
            try {
                if(response.isSuccessful){
                    result.postValue(true)
                } else
                    result.postValue(false)
            } catch (e: Throwable) {
                result.postValue(false)
            }
            EspressoIdlingResource.decrement()
        }
        return result
    }

    fun unFollowMosque(id: Int, idMosque: Int) : LiveData<Boolean> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<Boolean>()
        GlobalScope.launch {
            val response = service.unFollowMosque(id, idMosque)
            try {
                if(response.isSuccessful){
                    result.postValue(true)
                } else
                    result.postValue(false)
            } catch (e: Throwable) {
                result.postValue(false)
            }
            EspressoIdlingResource.decrement()
        }
        return result
    }

    /** RESEARCH **/

    fun getResearches(idCity:Int, latitude: Double, longitude: Double) : LiveData<ApiResponse<List<ResearchResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<ResearchResponse>>>()
        GlobalScope.launch {
            val response = service.getResearches(latitude, longitude, idCity)
            val responseBody = response.body()?.research
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.empty(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }

    fun getResearchesByUser(idUser:Int, latitude: Double, longitude: Double) : LiveData<ApiResponse<List<ResearchResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<ResearchResponse>>>()
        GlobalScope.launch {
            val response = service.getResearchesByUser(idUser, latitude, longitude)
            val responseBody = response.body()?.research
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.empty(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }

    fun getResearchDetail(idResearch:Int, idUser:Int, latitude: Double, longitude: Double) : LiveData<ApiResponse<List<ResearchDetailResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<ResearchDetailResponse>>>()
        GlobalScope.launch {
            val response = service.getResearchDetail(idResearch, idUser, latitude, longitude)
            val responseBody = response.body()?.data
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.empty(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }

    fun attendResearch(idUser: Int, idResearch: Int, idMosque: Int) : LiveData<Boolean> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<Boolean>()
        GlobalScope.launch {
            val response = service.attendResearch(idUser, idResearch, idMosque)
            try {
                if(response.isSuccessful){
                    result.postValue(true)
                } else
                    result.postValue(false)
            } catch (e: Throwable) {
                result.postValue(false)
            }
            EspressoIdlingResource.decrement()
        }
        return result
    }

    /** FRIDAY PRAYER **/

    fun getFridayPrayers(idUser: Int, latitude: Double, longitude: Double) : LiveData<ApiResponse<List<FridayPrayerResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<FridayPrayerResponse>>>()
        GlobalScope.launch {
            val response = service.getFridayPrayers(idUser, latitude, longitude)
            val responseBody = response.body()?.data
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.error(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }

    /** ANNOUNCEMENT **/

    fun getAnnouncements(idCity: Int, latitude: Double, longitude: Double) : LiveData<ApiResponse<List<AnnouncementResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<AnnouncementResponse>>>()
        GlobalScope.launch {
            val response = service.getAnnouncements(latitude, longitude, idCity)
            val responseBody = response.body()?.announcement
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.error(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }

    fun getAnnouncementsByUser(idUser: Int, latitude: Double, longitude: Double) : LiveData<ApiResponse<List<AnnouncementResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<AnnouncementResponse>>>()
        GlobalScope.launch {
            val response = service.getAnnouncementsByUser(idUser, latitude, longitude)
            val responseBody = response.body()?.announcement
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.error(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }

    /** CITY **/

    fun getCities() : LiveData<ApiResponse<List<CityResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<CityResponse>>>()
        GlobalScope.launch {
            val response = service.getCities()
            val responseBody = response.body()?.data
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.error(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }

    /** SHOLAT **/

    fun getSholatTimes(apiCode: Int, dateNow: Date) : LiveData<ApiResponse<List<SholatResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<SholatResponse>>>()
        GlobalScope.launch {
            val response = service.getSholatTimes(apiCode, dateNow)
            val responseBody = response.body()?.data
            try {
                if(response.isSuccessful){
                    if(responseBody != null)
                        result.postValue(ApiResponse.success(responseBody))
                    else
                        result.postValue(ApiResponse.empty(response.message()))
                } else
                    result.postValue(ApiResponse.error(response.message()))
                EspressoIdlingResource.decrement()
            } catch (e: Throwable) {
                result.postValue(ApiResponse.error(e.toString()))
            }
        }
        return result
    }
}

