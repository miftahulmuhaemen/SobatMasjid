package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class OfficerResponse(
    @SerializedName("id-mosque") var idMosque: String?,
    @SerializedName("date") var date: String?,
    @SerializedName("khatib") var khatib: String?,
    @SerializedName("muadzin") var muadzin: String?
)