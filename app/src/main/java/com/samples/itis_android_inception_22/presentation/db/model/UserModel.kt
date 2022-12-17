package com.samples.itis_android_inception_22.presentation.db.model

import androidx.room.Embedded

data class UserModel(
    val id: Int,
    val userName: String,
    val userPassword: String,
    @Embedded val addressModel: AddressModel
)

