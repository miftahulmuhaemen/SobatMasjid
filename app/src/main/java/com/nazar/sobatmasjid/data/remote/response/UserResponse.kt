package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id-user") var id: String?,
    @SerializedName("id-city") var idCity: String?,
    @SerializedName("api-code") var apiCode: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("name-city") var nameCity: String?,
    @SerializedName("born-date") var bornDate: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("gender") var gender: String?,
    @SerializedName("motto") var motto: String?,
    @SerializedName("number-follow") var numberFollow: String?,
    @SerializedName("photo") var photo: String?
)