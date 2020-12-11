package com.nazar.sobatmasjid.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "announcement")
data class AnnouncementEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "idMosque")
    var idMosque: String?,

    @ColumnInfo(name = "idCity")
    var idCity: String?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "date")
    var date: String?,

    @ColumnInfo(name = "category")
    var category: String?,

    @ColumnInfo(name = "file")
    var file: String?,

    @ColumnInfo(name = "mosqueName")
    var mosqueName: String?,

    @ColumnInfo(name = "mosqueType")
    var mosqueType: String?,

    @ColumnInfo(name = "isFollowedMosque")
    var isFollowedMosque: Boolean?
)