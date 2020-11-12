package com.nazar.sobatmasjid.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mosqueDetail")
data class MosqueDetailEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "latitude")
    var latitude: String?,

    @ColumnInfo(name = "longitude")
    var longitude: String?,

    @ColumnInfo(name = "username")
    var username: String?,

    @ColumnInfo(name = "distance")
    var distance: String?,

    @ColumnInfo(name = "type")
    var type: String?,

    @ColumnInfo(name = "classification")
    var classification: String?,

    @ColumnInfo(name = "email")
    var email: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "standingDate")
    var standingDate: String?,

    @ColumnInfo(name = "address")
    var address: String?,

    @ColumnInfo(name = "accountNumber")
    var accountNumber: String?,

    @ColumnInfo(name = "accountName")
    var accountName: String?,

    @ColumnInfo(name = "qris")
    var qris: String?,

    @ColumnInfo(name = "bankName")
    var bankName: String?,

    @ColumnInfo(name = "urbanVillage")
    var urbanVillage: String?,

    @ColumnInfo(name = "subDistrict")
    var subDistrict: String?,

    @ColumnInfo(name = "city")
    var city: String?,

    @ColumnInfo(name = "province")
    var province: String?,

    @ColumnInfo(name = "photo")
    var photo: String?,

    @ColumnInfo(name = "followed")
    var followed: Boolean
)