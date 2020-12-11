package com.nazar.sobatmasjid.data.remote.response

import com.squareup.moshi.Json

data class SholatResponse(
    @field:Json(name = "imsak") var imsak: String?,
    @field:Json(name = "shubuh") var shubuh: String?,
    @field:Json(name = "dzuhur") var dzuhur: String?,
    @field:Json(name = "ashar") var ashar: String?,
    @field:Json(name = "maghrib") var maghrib: String?,
    @field:Json(name = "isya") var isya: String?,
    @field:Json(name = "date") var date: String?
)