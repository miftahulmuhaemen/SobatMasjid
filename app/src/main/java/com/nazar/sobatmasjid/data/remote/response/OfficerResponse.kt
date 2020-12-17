package com.nazar.sobatmasjid.data.remote.response

import com.squareup.moshi.Json

data class OfficerResponse(
    @field:Json(name = "id-mosque") var idMosque: String?,
    @field:Json(name = "date") var date: String?,
    @field:Json(name = "khatib") var khatib: String?,
    @field:Json(name = "muadzin") var muadzin: String?
)