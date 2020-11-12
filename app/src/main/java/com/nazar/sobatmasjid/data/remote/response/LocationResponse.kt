package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("id-city") var id: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("latitude") var latitude: String?,
    @SerializedName("longitude") var longitude: String?,
    @SerializedName("distance") var distance: String?,
    @SerializedName("api-code") var apiCode: String?,
    @SerializedName("date-now") var dateNow: String?
)