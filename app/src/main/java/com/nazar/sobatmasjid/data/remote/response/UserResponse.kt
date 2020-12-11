package com.nazar.sobatmasjid.data.remote.response

import com.squareup.moshi.Json

data class UserResponse(
    @field:Json(name = "id-user") var id: String?,
    @field:Json(name = "id-city") var idCity: String?,
    @field:Json(name = "api-code") var apiCode: String?,
    @field:Json(name = "name") var name: String?,
    @field:Json(name = "name-city") var nameCity: String?,
    @field:Json(name = "born-date") var bornDate: String?,
    @field:Json(name = "email") var email: String?,
    @field:Json(name = "gender") var gender: String?,
    @field:Json(name = "motto") var motto: String?,
    @field:Json(name = "number-follow") var numberFollow: String?,
    @field:Json(name = "photo") var photo: String?
)