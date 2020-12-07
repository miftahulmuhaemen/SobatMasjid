package com.nazar.sobatmasjid.ui.fragments.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.remote.ApiResponse
import com.nazar.sobatmasjid.data.remote.response.UserResponse
import com.nazar.sobatmasjid.data.service.LocationService
import com.nazar.sobatmasjid.data.service.model.LocationModel
import java.util.*

class LoginViewModel (private val dataRepository: DataRepository) : ViewModel() {

    fun userLogin(name: String, date: Date, email: String, latitude: Double, longitude: Double): LiveData<ApiResponse<UserResponse>> =
        dataRepository.userLogin(name, date, email, latitude, longitude)

    fun getLocation(context: Context): LiveData<LocationModel> = LocationService(context)

}