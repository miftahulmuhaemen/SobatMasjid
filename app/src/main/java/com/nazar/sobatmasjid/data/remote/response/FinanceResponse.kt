package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class FinanceResponse(
    @SerializedName("no") var id: Int,
    @SerializedName("id-mosque") var idMosque: String?,
    @SerializedName("date") var date: String?,
    @SerializedName("credit-in") var creditIn: String?,
    @SerializedName("credit-out") var creditOut: String?,
    @SerializedName("credit-text") var creditText: String?
)