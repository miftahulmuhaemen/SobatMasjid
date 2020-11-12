package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomeListResponse(
    @SerializedName("status") var status: Boolean?,
    @SerializedName("location") var location: List<LocationResponse>?,
    @SerializedName("nearest-mosque") var nearestMosque: List<MosqueResponse>?,
    @SerializedName("recent-research") var research: List<ResearchResponse>?,
    @SerializedName("friday-prayer") var fridayPrayer: List<FridayPrayerResponse>?,
    @SerializedName("announcement") var announcement: List<AnnouncementResponse>?
)