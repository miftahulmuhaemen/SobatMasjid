package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class MosqueDetailListResponse(
    @SerializedName("status") var status: Boolean?,
    @SerializedName("mosque") var mosque: List<MosqueDetailResponse>?
)