package com.nazar.sobatmasjid.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "finance")
data class FinanceEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "idMosque")
    var idMosque: String?,

    @ColumnInfo(name = "date")
    var date: String?,

    @ColumnInfo(name = "creditIn")
    var creditIn: String?,

    @ColumnInfo(name = "creditOut")
    var creditOut: String?,

    @ColumnInfo(name = "creditText")
    var creditText: String?
)