package com.nazar.sobatmasjid.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "researchDetail")
data class ResearchDetailEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "idMosque")
    var idMosque: String?,

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

    @ColumnInfo(name = "brochure")
    var brochure: String?,

    @ColumnInfo(name = "link")
    var link: String?,

    @ColumnInfo(name = "note")
    var note: String?,

    @ColumnInfo(name = "followed")
    var followed: Boolean?
)