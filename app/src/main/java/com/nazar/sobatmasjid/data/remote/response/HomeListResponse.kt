package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class HomeListResponse(
    @field:Json(name = "status") var status: Boolean?,
    @field:Json(name = "location") var location: List<LocationResponse>?,
    @field:Json(name = "nearest-mosque") var nearestMosque: List<MosqueResponse>?,
    @field:Json(name = "recent-research") var research: List<ResearchResponse>?,
    @field:Json(name = "friday-prayer") var fridayPrayer: List<FridayPrayerResponse>?,
    @field:Json(name = "announcement") var announcement: List<AnnouncementResponse>?
)