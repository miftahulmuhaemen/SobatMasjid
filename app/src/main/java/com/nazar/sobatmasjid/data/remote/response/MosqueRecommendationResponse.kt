package com.nazar.sobatmasjid.data.remote.response

import com.squareup.moshi.Json

data class MosqueRecommendationResponse(
    @field:Json(name = "id-mosque") var id: String?,
    @field:Json(name = "id-city") var idCity: String?,
    @field:Json(name = "mosque-name") var mosqueName: String?,
    @field:Json(name = "photo") var photo: String?,
    @field:Json(name = "latitude") var latitude: String?,
    @field:Json(name = "longitude") var longitude: String?,
    @field:Json(name = "username") var username: String?,
    @field:Json(name = "distance") var distance: String?
)