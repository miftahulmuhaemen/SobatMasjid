package com.nazar.sobatmasjid.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mosque")
data class MosqueEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "idCity")
    var idCity: String?,

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

    @ColumnInfo(name = "photo")
    var photo: String?
)