package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class ResearchResponse(
    @field:Json(name = "id-research") var id: String?,
    @field:Json(name = "id-city") var idCity: String?,
    @field:Json(name = "id-mosque") var idMosque: String?,
    @field:Json(name = "title") var researchTitle: String?,
    @field:Json(name = "research-type") var researchType: String?,
    @field:Json(name = "date") var date: String?,
    @field:Json(name = "start-time") var startTime: String?,
    @field:Json(name = "end-time") var endTime: String?,
    @field:Json(name = "research-category") var category: String?,
    @field:Json(name = "ustadz-name") var ustadzName: String?,
    @field:Json(name = "photo") var ustadzPhoto: String?,
    @field:Json(name = "mosque-name") var mosqueName: String?,
    @field:Json(name = "mosque-type") var mosqueType: String?
)