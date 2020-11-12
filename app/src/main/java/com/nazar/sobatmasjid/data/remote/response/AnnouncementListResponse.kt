package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class AnnouncementListResponse(
    @SerializedName("status") var status: Boolean?,
    @SerializedName("location") var location: List<LocationResponse>?,
    @SerializedName("announcement") var announcement: List<AnnouncementResponse>?
)