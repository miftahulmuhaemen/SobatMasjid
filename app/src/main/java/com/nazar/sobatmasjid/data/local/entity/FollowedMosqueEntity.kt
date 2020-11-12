package com.nazar.sobatmasjid.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "followedMosque")
data class FollowedMosqueEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "photo")
    var photo: String?,

    @ColumnInfo(name = "username")
    var username: String?
)