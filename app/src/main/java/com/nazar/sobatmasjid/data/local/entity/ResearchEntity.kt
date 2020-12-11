package com.nazar.sobatmasjid.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "research")
data class ResearchEntity(
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

    @ColumnInfo(name = "researchType")
    var researchType: String?,

    @ColumnInfo(name = "date")
    var date: String?,

    @ColumnInfo(name = "startTime")
    var startTime: String?,

    @ColumnInfo(name = "endTime")
    var endTime: String?,

    @ColumnInfo(name = "category")
    var category: String?,

    @ColumnInfo(name = "ustadzName")
    var ustadzName: String?,

    @ColumnInfo(name = "ustadzPhoto")
    var ustadzPhoto: String?,

    @ColumnInfo(name = "mosqueName")
    var mosqueName: String?,

    @ColumnInfo(name = "mosqueType")
    var mosqueType: String?,

    @ColumnInfo(name = "isFollowedMosque")
    var isFollowedMosque: Boolean?
)