package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class DefaultResponse(
    @SerializedName("status") var status: Boolean?,
    @SerializedName("message") var message: String?
)