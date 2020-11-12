package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class SholatResponse(
    @SerializedName("imsak") var imsak: String?,
    @SerializedName("shubuh") var shubuh: String?,
    @SerializedName("dzuhur") var dzuhur: String?,
    @SerializedName("ashar") var ashar: String?,
    @SerializedName("maghrib") var maghrib: String?,
    @SerializedName("isya") var isya: String?,
    @SerializedName("date") var date: String?
)