package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class MosqueResponse(
    @SerializedName("id-mosque") var id: String?,
    @SerializedName("id-city") var idCity: String?,
    @SerializedName("mosque-name") var name: String?,
    @SerializedName("photo") var photo: String?,
    @SerializedName("latitude") var latitude: String?,
    @SerializedName("longitude") var longitude: String?,
    @SerializedName("username") var username: String?,
    @SerializedName("distance") var distance: String?,
    @SerializedName("mosque-type") var type: String?,
    @SerializedName("mosque-classification") var classification: String?
)