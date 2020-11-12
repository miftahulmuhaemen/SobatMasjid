package com.nazar.sobatmasjid.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sholat")
data class SholatEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "date")
    var date: String?,

    @ColumnInfo(name = "imsak")
    var imsak: String?,

    @ColumnInfo(name = "shubuh")
    var shubuh: String?,

    @ColumnInfo(name = "dzuhur")
    var dzuhur: String?,

    @ColumnInfo(name = "ashar")
    var ashar: String?,

    @ColumnInfo(name = "maghrib")
    var maghrib: String?,

    @ColumnInfo(name = "isya")
    var isya: String?
)