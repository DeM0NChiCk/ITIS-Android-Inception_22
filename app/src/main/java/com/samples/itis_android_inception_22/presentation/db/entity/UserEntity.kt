package com.samples.itis_android_inception_22.presentation.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.samples.itis_android_inception_22.presentation.db.model.AddressModel

@Entity(
    tableName = "users",
    indices = [
        Index("username", unique = true)
    ]
)
data class UserEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "username") val userName: String,
    @ColumnInfo(name = "password") val userPassword: String,
    @Embedded val address: AddressModel
)
