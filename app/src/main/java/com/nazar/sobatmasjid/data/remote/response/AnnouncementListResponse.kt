package com.nazar.sobatmasjid.data.remote.response

import com.squareup.moshi.Json

data class AnnouncementListResponse(
    @field:Json(name = "status") var status: Boolean?,
    @field:Json(name = "location") var location: List<LocationResponse>?,
    @field:Json(name = "announcement") var announcement: List<AnnouncementResponse>?
)