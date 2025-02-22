package com.nazar.sobatmasjid.data.remote.response

import com.squareup.moshi.Json

data class LocationResponse(
    @field:Json(name = "id-city") var id: String?,
    @field:Json(name = "name") var name: String?,
    @field:Json(name = "latitude") var latitude: String?,
    @field:Json(name = "longitude") var longitude: String?,
    @field:Json(name = "distance") var distance: String?,
    @field:Json(name = "api-code") var apiCode: String?,
    @field:Json(name = "date-now") var dateNow: String?
)