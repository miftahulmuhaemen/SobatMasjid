package com.nazar.sobatmasjid.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityEntity(
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

    @ColumnInfo(name = "apiCode")
    var apiCode: String?
)