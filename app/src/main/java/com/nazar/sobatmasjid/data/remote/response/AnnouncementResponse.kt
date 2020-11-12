package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class AnnouncementResponse(
    @SerializedName("id-announcement") var id: String?,
    @SerializedName("id-city") var idCity: String?,
    @SerializedName("id-mosque") var idMosque: String?,
    @SerializedName("title") var title: String?,
    @SerializedName("date") var date: String?,
    @SerializedName("announcement-category") var category: String?,
    @SerializedName("file") var file: String?,
    @SerializedName("mosque-name") var mosqueName: String?,
    @SerializedName("mosque-type") var mosqueType: String?
)