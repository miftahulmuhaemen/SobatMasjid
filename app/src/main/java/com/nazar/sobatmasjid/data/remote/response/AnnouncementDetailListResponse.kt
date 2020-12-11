package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class AnnouncementDetailListResponse(
    @field:Json(name = "status") var status: Boolean?,
    @field:Json(name = "data") var announcement: List<AnnouncementResponse>?
)