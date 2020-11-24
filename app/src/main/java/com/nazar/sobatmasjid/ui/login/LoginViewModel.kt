package com.nazar.sobatmasjid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.remote.ApiResponse
import com.nazar.sobatmasjid.data.remote.response.UserResponse
import java.util.*

class LoginViewModel (private val dataRepository: DataRepository) : ViewModel() {

    fun userLogin(name: String, date: Date, email: String, latitude: Double, longitude: Double): LiveData<ApiResponse<UserResponse>> =
        dataRepository.userLogin(name, date, email, latitude, longitude)

}