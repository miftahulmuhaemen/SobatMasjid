package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class FridayPrayerListResponse(
    @SerializedName("status") var status: Boolean?,
    @SerializedName("data") var data: List<FridayPrayerResponse>?
)