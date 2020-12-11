package com.nazar.sobatmasjid.data.remote.response

import com.squareup.moshi.Json

data class SholatListResponse(
    @field:Json(name = "status") var status: Boolean?,
    @field:Json(name = "sholat-time") var data: List<SholatResponse>?
)