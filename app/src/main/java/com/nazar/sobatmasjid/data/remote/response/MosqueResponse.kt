package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class MosqueResponse(
    @field:Json(name = "id-mosque") var id: String?,
    @field:Json(name = "id-city") var idCity: String?,
    @field:Json(name = "mosque-name") var name: String?,
    @field:Json(name = "photo") var photo: String?,
    @field:Json(name = "latitude") var latitude: String?,
    @field:Json(name = "longitude") var longitude: String?,
    @field:Json(name = "username") var username: String?,
    @field:Json(name = "distance") var distance: String?,
    @field:Json(name = "mosque-type") var type: String?,
    @field:Json(name = "mosque-classification") var classification: String?
)