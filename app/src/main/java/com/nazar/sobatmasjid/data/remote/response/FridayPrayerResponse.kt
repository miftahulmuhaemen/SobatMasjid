package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class FridayPrayerResponse(
    @SerializedName("id-mosque") var id: String?,
    @SerializedName("id-city") var idCity: String?,
    @SerializedName("mosque-name") var mosqueName: String?,
    @SerializedName("mosque-type") var mosqueType: String?,
    @SerializedName("credit-in") var creditIn: String?,
    @SerializedName("credit-out") var creditOut: String?,
    @SerializedName("credit-text") var creditText: String?,
    @SerializedName("date") var date: String?,
    @SerializedName("khatib") var khatib: String?,
    @SerializedName("muadzin") var muadzin: String?
)