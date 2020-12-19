package com.nazar.sobatmasjid.ui.fragments.profile.edit

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nazar.sobatmasjid.BuildConfig.API_KEY
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.data.local.entity.FollowedMosqueEntity
import com.nazar.sobatmasjid.data.remote.ApiResponse
import com.nazar.sobatmasjid.vo.Resource
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*


class ProfileEditViewModel(private val dataRepository: DataRepository) : ViewModel() {
    var bornDate: MutableLiveData<String> = MutableLiveData()
    var gender: MutableLiveData<String> = MutableLiveData()
    fun setBornDate(data: String) {
        bornDate.value = data
    }

    fun setGender(data: String) {
        gender.value = data
    }

    fun getFollowedMosques(
        id: String,
        query: String
    ): LiveData<Resource<PagedList<FollowedMosqueEntity>>> =
        dataRepository.getFollowedMosques(id, query)

    fun updateUser(
        idUser: String,
        photo: File,
        name: String,
        bornDate: String,
        email: String,
        gender: String,
        motto: String
    ): LiveData<ApiResponse<Boolean>> {
        val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart("id-user", idUser)
            .addFormDataPart("name", name)
            .addFormDataPart("born-date", bornDate)
            .addFormDataPart("email", email)
            .addFormDataPart("gender", gender)
            .addFormDataPart("motto", motto)
            .addFormDataPart("API-KEY", API_KEY)
            .addFormDataPart("photo", photo.name, photo.asRequestBody("image/jpeg".toMediaType()))
        val requestBody = builder.build()
        return dataRepository.updateUser(idUser, requestBody)
    }
}