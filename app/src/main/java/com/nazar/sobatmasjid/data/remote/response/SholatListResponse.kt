package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class SholatListResponse(
    @SerializedName("status") var status: Boolean?,
    @SerializedName("sholat-time") var data: List<SholatResponse>?
)