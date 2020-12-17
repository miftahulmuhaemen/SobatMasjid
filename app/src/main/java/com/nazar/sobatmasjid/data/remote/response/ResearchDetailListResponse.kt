package com.nazar.sobatmasjid.data.remote.response

import com.squareup.moshi.Json

data class ResearchDetailListResponse(
    @field:Json(name = "status") var status: Boolean?,
    @field:Json(name = "research") var data: List<ResearchDetailResponse>?
)