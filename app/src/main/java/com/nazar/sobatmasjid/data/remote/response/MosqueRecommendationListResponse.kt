package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class MosqueRecommendationListResponse(
    @SerializedName("status") var status: Boolean?,
    @SerializedName("data") var data: List<MosqueRecommendationResponse>?
)