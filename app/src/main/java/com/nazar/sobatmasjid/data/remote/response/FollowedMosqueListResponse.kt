package com.nazar.sobatmasjid.data.remote.response

import com.squareup.moshi.Json

data class FollowedMosqueListResponse(
    @field:Json(name = "status") var status: Boolean?,
    @field:Json(name = "data") var data: List<FollowedMosqueResponse>?
)