package com.samples.itis_android_inception_22.presentation.db.model

import androidx.room.Embedded

data class UserUpdateModel(
    val id: Int,
    @Embedded val addressModel: AddressModel
)
