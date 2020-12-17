package com.nazar.sobatmasjid.data.remote.response

import com.squareup.moshi.Json

data class FollowedMosqueResponse(
    @field:Json(name = "id-mosque") var id: String?,
    @field:Json(name = "mosque-name") var mosqueName: String?,
    @field:Json(name = "photo") var photo: String?,
    @field:Json(name = "username") var username: String?
)