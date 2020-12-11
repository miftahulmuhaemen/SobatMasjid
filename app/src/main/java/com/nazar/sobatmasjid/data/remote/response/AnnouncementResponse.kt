package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class AnnouncementResponse(
    @field:Json(name = "id-announcement") var id: String?,
    @field:Json(name = "id-city") var idCity: String?,
    @field:Json(name = "id-mosque") var idMosque: String?,
    @field:Json(name = "title") var title: String?,
    @field:Json(name = "date") var date: String?,
    @field:Json(name = "announcement-category") var category: String?,
    @field:Json(name = "file") var file: String?,
    @field:Json(name = "mosque-name") var mosqueName: String?,
    @field:Json(name = "mosque-type") var mosqueType: String?
)