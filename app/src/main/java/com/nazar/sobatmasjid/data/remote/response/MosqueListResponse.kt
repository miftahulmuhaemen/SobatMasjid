package com.nazar.sobatmasjid.data.remote.response

import com.squareup.moshi.Json

data class MosqueListResponse(
    @field:Json(name = "status") var status: Boolean?,
    @field:Json(name = "location") var location: List<LocationResponse>?,
    @field:Json(name = "mosque") var mosque: List<MosqueResponse>?
)