package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResearchDetailResponse(
    @SerializedName("id-research") var id: String?,
    @SerializedName("id-city") var idCity: String?,
    @SerializedName("title") var researchTitle: String?,
    @SerializedName("research-type") var researchType: String?,
    @SerializedName("date") var researchDate: String?,
    @SerializedName("start-time") var startTime: String?,
    @SerializedName("end-time") var endTime: String?,
    @SerializedName("research-category") var category: String?,
    @SerializedName("ustadz-name") var ustadzName: String?,
    @SerializedName("photo") var ustadzPhoto: String?,
    @SerializedName("mosque-name") var mosqueName: String?,
    @SerializedName("id-mosque") var idMosque: String?,
    @SerializedName("brochure") var brochure: String?,
    @SerializedName("link") var link: String?,
    @SerializedName("note") var note: String?,
    @SerializedName("mosque-type") var mosqueType: String?,
    @SerializedName("attend") var attend: Boolean?
)