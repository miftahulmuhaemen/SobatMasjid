package com.nazar.sobatmasjid.data.remote.response

import com.squareup.moshi.Json

data class FridayPrayerResponse(
    @field:Json(name = "id-mosque") var idMosque: String?,
    @field:Json(name = "id-city") var idCity: String?,
    @field:Json(name = "mosque-name") var mosqueName: String?,
    @field:Json(name = "mosque-type") var mosqueType: String?,
    @field:Json(name = "credit-in") var creditIn: String?,
    @field:Json(name = "credit-out") var creditOut: String?,
    @field:Json(name = "credit-text") var creditText: String?,
    @field:Json(name = "date") var date: String?,
    @field:Json(name = "khatib") var khatib: String?,
    @field:Json(name = "muadzin") var muadzin: String?
)