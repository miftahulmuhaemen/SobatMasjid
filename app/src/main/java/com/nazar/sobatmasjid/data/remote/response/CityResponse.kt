package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class CityResponse(
    @field:Json(name = "id-city") var id: String?,
    @field:Json(name = "name") var name: String?,
    @field:Json(name = "latitude") var latitude: String?,
    @field:Json(name = "longitude") var longitude: String?,
    @field:Json(name = "api-code") var apiCode: String?
)