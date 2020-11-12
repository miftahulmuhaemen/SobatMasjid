package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class FollowedMosqueResponse(
    @SerializedName("id-mosque") var id: String?,
    @SerializedName("mosque-name") var mosqueName: String?,
    @SerializedName("photo") var photo: String?,
    @SerializedName("username") var username: String?
)