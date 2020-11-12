package com.nazar.sobatmasjid.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "bornDate")
    var bornDate: String?,

    @ColumnInfo(name = "email")
    var email: String?,

    @ColumnInfo(name = "gender")
    var gender: String?,

    @ColumnInfo(name = "numberFollow")
    var numberFollow: String?,

    @ColumnInfo(name = "photo")
    var photo: String?,

    @ColumnInfo(name = "motto")
    var motto: String?
)