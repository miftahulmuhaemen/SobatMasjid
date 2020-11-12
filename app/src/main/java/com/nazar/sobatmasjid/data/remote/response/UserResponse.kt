package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id-user") var id: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("born-date") var bornData: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("gender") var gender: String?,
    @SerializedName("motto") var motto: String?,
    @SerializedName("number-follow") var numberFollow: String?,
    @SerializedName("photo") var photo: String?
)