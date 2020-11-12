package com.nazar.sobatmasjid.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "officer")
data class OfficerEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    /** Possible Duplicate because no restraint **/

    @ColumnInfo(name = "idMosque")
    var idMosque: String?,

    @ColumnInfo(name = "date")
    var date: String?,

    @ColumnInfo(name = "khatib")
    var khatib: String?,

    @ColumnInfo(name = "muadzin")
    var muadzin: String?
)