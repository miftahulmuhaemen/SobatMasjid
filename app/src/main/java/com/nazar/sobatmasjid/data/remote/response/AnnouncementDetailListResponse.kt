package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class AnnouncementDetailListResponse(
    @SerializedName("status") var status: Boolean?,
    @SerializedName("data") var announcement: List<AnnouncementResponse>?
)