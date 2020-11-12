package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class MosqueListResponse(
    @SerializedName("status") var status: Boolean?,
    @SerializedName("location") var location: List<LocationResponse>?,
    @SerializedName("mosque") var mosque: List<MosqueResponse>?
)