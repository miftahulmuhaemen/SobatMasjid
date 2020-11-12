package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResearchListResponse(
    @SerializedName("status") var status: Boolean?,
    @SerializedName("location") var location: List<LocationResponse>?,
    @SerializedName("research") var research: List<ResearchResponse>?
)