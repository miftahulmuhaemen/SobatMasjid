package com.nazar.sobatmasjid.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fridayPrayer")
data class FridayPrayerEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idMosque")
    var idMosque: String,

    @ColumnInfo(name = "idCity")
    var idCity: String?,

    @ColumnInfo(name = "mosqueName")
    var mosqueName: String?,

    @ColumnInfo(name = "mosqueType")
    var mosqueType: String?,

    @ColumnInfo(name = "creditIn")
    var creditIn: String?,

    @ColumnInfo(name = "creditOut")
    var creditOut: String?,

    @ColumnInfo(name = "creditText")
    var creditText: String?,

    @ColumnInfo(name = "date")
    var date: String?,

    @ColumnInfo(name = "khatib")
    var khatib: String?,

    @ColumnInfo(name = "muadzin")
    var muadzin: String?
)