package com.nazar.sobatmasjid.data.remote.response

import com.squareup.moshi.Json

data class FinanceResponse(
    @field:Json(name = "no") var id: Int,
    @field:Json(name = "id-mosque") var idMosque: String?,
    @field:Json(name = "date") var date: String?,
    @field:Json(name = "credit-in") var creditIn: String?,
    @field:Json(name = "credit-out") var creditOut: String?,
    @field:Json(name = "credit-text") var creditText: String?
)