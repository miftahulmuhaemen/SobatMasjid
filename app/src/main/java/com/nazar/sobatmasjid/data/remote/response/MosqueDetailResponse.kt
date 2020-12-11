package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class MosqueDetailResponse(
    @field:Json(name = "id-mosque") var id: String?,
    @field:Json(name = "id-city") var idCity: String?,
    @field:Json(name = "mosque-name") var name: String?,
    @field:Json(name = "photo") var photo: String?,
    @field:Json(name = "email") var email: String?,
    @field:Json(name = "description") var description: String?,
    @field:Json(name = "standing-date") var standingDate: String?,
    @field:Json(name = "address") var address: String?,
    @field:Json(name = "account-number") var accountNumber: String?,
    @field:Json(name = "account-name") var accountName: String?,
    @field:Json(name = "qris") var qris: String?,
    @field:Json(name = "bank-name") var bankName: String?,
    @field:Json(name = "urban-village") var urbanVillage: String?,
    @field:Json(name = "sub-district") var subDistrict: String?,
    @field:Json(name = "city") var city: String?,
    @field:Json(name = "province") var province: String?,
    @field:Json(name = "latitude") var latitude: String?,
    @field:Json(name = "longitude") var longitude: String?,
    @field:Json(name = "username") var username: String?,
    @field:Json(name = "distance") var distance: String?,
    @field:Json(name = "mosque-type") var type: String?,
    @field:Json(name = "mosque-classification") var classification: String?,
    @field:Json(name = "research") var research: List<ResearchResponse>?,
    @field:Json(name = "announcement") var announcement: List<AnnouncementResponse>?,
    @field:Json(name = "officer") var officer: List<OfficerResponse>?,
    @field:Json(name = "finance") var finance: List<FinanceResponse>?,
    @field:Json(name = "followed") var followed: Boolean = false
)