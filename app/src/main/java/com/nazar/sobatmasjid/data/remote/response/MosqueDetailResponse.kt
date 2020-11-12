package com.nazar.sobatmasjid.data.remote.response

import com.google.gson.annotations.SerializedName

data class MosqueDetailResponse(
    @SerializedName("id-mosque") var id: String?,
    @SerializedName("id-city") var idCity: String?,
    @SerializedName("mosque-name") var name: String?,
    @SerializedName("photo") var photo: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("standing-date") var standingDate: String?,
    @SerializedName("address") var address: String?,
    @SerializedName("account-number") var accountNumber: String?,
    @SerializedName("account-name") var accountName: String?,
    @SerializedName("qris") var qris: String?,
    @SerializedName("bank-name") var bankName: String?,
    @SerializedName("urban-village") var urbanVillage: String?,
    @SerializedName("sub-district") var subDistrict: String?,
    @SerializedName("city") var city: String?,
    @SerializedName("province") var province: String?,
    @SerializedName("latitude") var latitude: String?,
    @SerializedName("longitude") var longitude: String?,
    @SerializedName("username") var username: String?,
    @SerializedName("distance") var distance: String?,
    @SerializedName("mosque-type") var type: String?,
    @SerializedName("mosque-classification") var classification: String?,
    @SerializedName("research") var research: List<ResearchResponse>?,
    @SerializedName("announcement") var announcement: List<AnnouncementResponse>?,
    @SerializedName("officer") var officer: List<OfficerResponse>?,
    @SerializedName("finance") var finance: List<FinanceResponse>?,
    @SerializedName("followed") var followed: Boolean = false
)